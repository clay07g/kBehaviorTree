package com.claygillman.kbehaviortree

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

class BehaviorTreeTest {
    @Test
    fun `Verify basic tree`() {
        var loopCount = 0
        val taskOutcomes = arrayOf(false, false, false, false, false)

        behaviorTree<Any> {
            sequence {
                selector {
                    condition { false }
                    condition {
                        taskOutcomes[0] = true
                        true
                    }
                    perform { fail("This selector should have ended") }
                }
                sequence {
                    behavior {
                        taskOutcomes[1] = true
                        BehaviorTreeStatus.SUCCESS
                    }
                    perform {
                        taskOutcomes[2] = true
                    }
                    condition {
                        taskOutcomes[3] = true
                        true
                    }
                    repeatUntil({ loopCount == 5 }) {
                        perform { loopCount++ }
                    }
                    condition {
                        taskOutcomes[4] = true
                        false
                    }
                    perform { fail("This sequence should have ended") }
                }
                perform { fail("This sequence should have ended") }
            }
        }.tick()

        Assertions.assertEquals(5, loopCount)
        taskOutcomes.forEach(::assert)
    }
}
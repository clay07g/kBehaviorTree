# kBehaviorTree
Logical framework for implementing Behavior Trees in the context of game/robotics AI.

Based on [Behavior trees for AI: How they work](https://www.gamasutra.com/blogs/ChrisSimpson/20140717/221339/Behavior_trees_for_AI_How_they_work.php)

This implementation uses Kotlin's [Type-Safe Builders](https://kotlinlang.org/docs/reference/type-safe-builders.html), which
feel like a DSL for constructing behavior trees. The result is a syntax that has very little bloat, yet is comprised of
type-safe constructs.

# Usage

```kotlin
fun main() {
    behaviorTree<Any> {
        sequence {
            behavior {
                // Do stuff
                BehaviorTreeStatus.SUCCESS
            }
            repeatUntil(BehaviorTreeStatus.SUCCESS) { // Repeats the selector until it succeeds
                selector {
                    condition { doesThingExist() }
                    perform { makeThingExist() }
                }
            }
        }
    }
}
```

The API also allows you to splice trees together, allowing you to organize your logic into separate modules.

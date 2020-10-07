package com.claygillman.kbehaviortree.nodes

import com.claygillman.kbehaviortree.BehaviorTreeStatus
import com.claygillman.kbehaviortree.Composite

/**
 * Composite Node
 * A sequence node will run its children until 1 fails.
 * If a child fails, the sequence will report failure.
 * If all the nodes run and succeed, the sequence will report success.
 */
class SequenceNode(private var _name: String = "") : Composite() {

    override var name: String
        get() = "[Sequence] $_name"
        set(n) {
            this._name = n
        }

    override fun tick(): BehaviorTreeStatus {
        for (node in children) {
            var status: BehaviorTreeStatus
            do {
                status = node.tick()
            } while (status === BehaviorTreeStatus.RUNNING)

            if (status !== BehaviorTreeStatus.SUCCESS)
                return status
        }

        return BehaviorTreeStatus.SUCCESS
    }
}
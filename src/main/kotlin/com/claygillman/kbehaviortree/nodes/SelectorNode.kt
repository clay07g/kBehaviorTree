package com.claygillman.kbehaviortree.nodes

import com.claygillman.kbehaviortree.BehaviorTreeStatus
import com.claygillman.kbehaviortree.Composite

/**
 * Composite Node
 * A selector node will run its children until 1 succeeds.
 * If a child succeeds, the selector will report success.
 * If all the children run and fail, the selector will report failure.
 */
class SelectorNode(private var _name: String = "") : Composite() {

    override var name: String
        get() = "[Selector] $_name"
        set(n) {
            this._name = n
        }

    override fun tick(): BehaviorTreeStatus {
        for (node in children) {
            var status: BehaviorTreeStatus
            do {
                status = node.tick()
            } while (status === BehaviorTreeStatus.RUNNING)

            if (status !== BehaviorTreeStatus.FAILURE)
                return status
        }

        return BehaviorTreeStatus.FAILURE
    }
}
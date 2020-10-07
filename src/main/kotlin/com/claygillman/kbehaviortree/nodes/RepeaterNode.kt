package com.claygillman.kbehaviortree.nodes

import com.claygillman.kbehaviortree.BehaviorTreeStatus
import com.claygillman.kbehaviortree.Decorator

/**
 * Decorator Node
 * A repeater node will run its child in an indefinite loop or until its child returns KILL
 */
class RepeaterNode(private var _name: String = "") : Decorator() {
    override var name: String
        get() = "[Repeater] $_name"
        set(n) {
            this._name = n
        }

    override fun tick(): BehaviorTreeStatus {
        if (child == null)
            throw RuntimeException("Repeater node ($name) has no children")

        while (true) {
            if (child!!.tick() == BehaviorTreeStatus.KILL)
                break
        }

        return BehaviorTreeStatus.KILL
    }
}
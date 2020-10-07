package com.claygillman.kbehaviortree.nodes

import com.claygillman.kbehaviortree.BehaviorTreeStatus
import com.claygillman.kbehaviortree.Decorator

/**
 * Decorator Node
 * A succeeder node's tick always reports success regardless of the actual result of its child
 */
class SucceederNode(private var _name: String = "") : Decorator() {
    override var name: String
        get() = "[Succeeder] $_name"
        set(n) {
            this._name = n
        }

    override fun tick(): BehaviorTreeStatus {
        if (child == null)
            throw RuntimeException("Succeeder node ($name) has no children")

        child!!.tick()

        return BehaviorTreeStatus.SUCCESS
    }
}
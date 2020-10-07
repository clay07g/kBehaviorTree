package com.claygillman.kbehaviortree.nodes

import com.claygillman.kbehaviortree.BehaviorTreeStatus
import com.claygillman.kbehaviortree.Decorator

class InverterNode(private var _name: String = "") : Decorator() {
    override var name: String
        get() = "[Inverter] $_name"
        set(n) {
            this._name = n
        }

    override fun tick(): BehaviorTreeStatus {
        if (child == null)
            throw RuntimeException("Inverter node ($name) has no children")

        val status = child!!.tick()

        return when {
            status === BehaviorTreeStatus.SUCCESS -> BehaviorTreeStatus.FAILURE
            status === BehaviorTreeStatus.FAILURE -> BehaviorTreeStatus.SUCCESS
            else -> status
        }
    }
}
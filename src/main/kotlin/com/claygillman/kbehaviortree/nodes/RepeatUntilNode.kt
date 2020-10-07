package com.claygillman.kbehaviortree.nodes

import com.claygillman.kbehaviortree.BehaviorTreeStatus
import com.claygillman.kbehaviortree.Decorator

class RepeatUntilNode() : Decorator() {
    private var _name: String = ""
    override var name: String
        get() = "[RepeatUntil] $_name"
        set(n) {
            this._name = n
        }

    private var status: BehaviorTreeStatus? = null
    private var stopCondition: (() -> Boolean)? = null

    constructor(name: String = "", stopCondition: () -> Boolean, status: BehaviorTreeStatus) : this() {
        this.name = name
        this.stopCondition = stopCondition
        this.status = status
    }

    constructor(name: String = "", stopCondition: () -> Boolean) : this() {
        this.name = name
        this.stopCondition = stopCondition
    }

    constructor(name: String = "", status: BehaviorTreeStatus) : this() {
        this.name = name
        this.status = status
    }

    override fun tick(): BehaviorTreeStatus {
        if (child == null)
            throw RuntimeException("RepeatUntil node ($name) has no children")

        var childStatus = child!!.tick()

        if (stopCondition == null) {
            while (childStatus !== this.status) {
                childStatus = child!!.tick()
                if (childStatus == BehaviorTreeStatus.KILL)
                    break
            }
        } else {
            while (childStatus !== this.status && !stopCondition!!()) {
                childStatus = child!!.tick()
                if (childStatus == BehaviorTreeStatus.KILL)
                    break
            }
        }

        return childStatus
    }
}
package com.claygillman.kbehaviortree

interface IBehaviorNode {
    var name: String

    fun tick(): BehaviorTreeStatus

    fun getTreeString(prefix: String = ""): String {
        return prefix + name
    }
}
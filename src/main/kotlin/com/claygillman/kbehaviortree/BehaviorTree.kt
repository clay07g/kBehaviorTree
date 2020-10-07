package com.claygillman.kbehaviortree

/**
 * Convenience node for acting as the root of a behavior tree.
 */
class BehaviorTree<T>(var dataContext: T? = null) : Decorator() {
    override var name: String = "Root"

    override fun tick(): BehaviorTreeStatus {
        return this.child?.tick() ?: BehaviorTreeStatus.FAILURE
    }

    override fun getTreeString(prefix: String): String {
        return name + "\n" + child?.getTreeString("    ")
    }
}
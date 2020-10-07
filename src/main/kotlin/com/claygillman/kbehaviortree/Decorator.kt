package com.claygillman.kbehaviortree

@DslMarker
abstract class Decorator : IParentNode {
    var child: IBehaviorNode? = null

    override fun <T : IBehaviorNode> initNode(name: String, node: T, init: T.() -> Unit): T {
        if (child != null)
            throw RuntimeException("Cannot add more than 1 child to ${this.javaClass.name} ($name)")

        node.init()
        node.name = name
        child = node
        return node
    }

    override fun getTreeString(prefix: String): String {
        var baseString = prefix + name

        baseString += "\n    " + prefix + child?.getTreeString("$prefix    ")

        return baseString
    }
}
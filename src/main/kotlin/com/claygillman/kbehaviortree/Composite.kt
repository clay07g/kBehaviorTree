package com.claygillman.kbehaviortree

@DslMarker
abstract class Composite : IParentNode {
    var children = arrayListOf<IBehaviorNode>()

    override fun <T : IBehaviorNode> initNode(name: String, node: T, init: T.() -> Unit): T {
        node.init()
        node.name = name
        children.add(node)
        return node
    }

    override fun getTreeString(prefix: String): String {
        var baseString = prefix + name

        for (child in children) {
            baseString += "\n    " + prefix + child.getTreeString("$prefix    ")
        }

        return baseString
    }
}
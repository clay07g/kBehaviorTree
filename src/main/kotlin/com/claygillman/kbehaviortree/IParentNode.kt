package com.claygillman.kbehaviortree

interface IParentNode : IBehaviorNode {
    fun <T : IBehaviorNode> initNode(name: String, node: T, init: T.() -> Unit): T
}
package com.claygillman.kbehaviortree

import com.claygillman.kbehaviortree.nodes.*

fun <T> behaviorTree(init: BehaviorTree<T>.() -> Unit): BehaviorTree<T> {
    val bt = BehaviorTree<T>()
    bt.init()
    return bt
}

fun IParentNode.selector(name: String = "", init: SelectorNode.() -> Unit) = this.initNode(name, SelectorNode(), init)
fun IParentNode.sequence(name: String = "", init: SequenceNode.() -> Unit) = this.initNode(name, SequenceNode(), init)
fun IParentNode.inverter(name: String = "", init: InverterNode.() -> Unit) = this.initNode(name, InverterNode(), init)
fun IParentNode.repeater(name: String = "", init: RepeaterNode.() -> Unit) = this.initNode(name, RepeaterNode(), init)
fun IParentNode.succeeder(name: String = "", init: SucceederNode.() -> Unit) =
    this.initNode(name, SucceederNode(), init)

fun IParentNode.repeatUntil(status: BehaviorTreeStatus, init: RepeatUntilNode.() -> Unit) =
    this.initNode("", RepeatUntilNode(status = status), init)

fun IParentNode.repeatUntil(condition: () -> Boolean, init: RepeatUntilNode.() -> Unit) =
    this.initNode("", RepeatUntilNode(stopCondition = condition), init)

fun IParentNode.repeatUntil(condition: () -> Boolean, status: BehaviorTreeStatus, init: RepeatUntilNode.() -> Unit) =
    this.initNode("", RepeatUntilNode(stopCondition = condition, status = status), init)

fun IParentNode.behavior(node: IBehaviorNode) = this.initNode(node.name, node, {})
fun IParentNode.behavior(name: String = "", func: () -> BehaviorTreeStatus) {
    val node = object : IBehaviorNode {
        override var name: String = name

        override fun tick(): BehaviorTreeStatus = func()
    }

    this.initNode("[Action] $name", node, {})
}

fun IParentNode.perform(name: String = "", func: () -> Unit) {
    val node = object : IBehaviorNode {
        override var name: String = ""

        override fun tick(): BehaviorTreeStatus {
            func()
            return BehaviorTreeStatus.SUCCESS
        }
    }

    this.initNode("[Perform] $name", node, {})
}

fun IParentNode.condition(name: String = "", func: () -> Boolean?) {
    val node = object : IBehaviorNode {
        override var name: String = ""

        override fun tick(): BehaviorTreeStatus {
            return if (func() == true)
                BehaviorTreeStatus.SUCCESS
            else
                BehaviorTreeStatus.FAILURE
        }
    }

    this.initNode("[Condition] $name", node, {})
}
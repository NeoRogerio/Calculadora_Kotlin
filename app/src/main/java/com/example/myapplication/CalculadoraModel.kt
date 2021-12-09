package com.example.myapplication

import io.realm.RealmObject
import io.realm.annotations.Required

open class ExpressionsList : RealmObject() {
    @Required
    var expressionResult: String = ""
}
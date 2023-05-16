package com.example.mychartapp


class ModelClass {
    lateinit var Key: String
    lateinit var receiveMessage: String

    constructor(key: String, massage: String) {
        this.Key = key
        receiveMessage = massage
    }

    constructor() {

    }
}
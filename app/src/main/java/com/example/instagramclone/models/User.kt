package com.example.instagramclone.models

class User {
    private var image: String?=null
    private var name: String?=null
    private var email: String?=null
    private var password: String?=null

    constructor()
    constructor(image:String?, name:String?, email:String?, password:String?){
        this.image = image
        this.name = name
        this.email = email
        this.password = password
    }
    constructor( name:String?, email:String?, password:String?){
        this.name = name
        this.email = email
        this.password = password
    }
}
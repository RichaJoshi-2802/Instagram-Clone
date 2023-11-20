package com.example.instagramclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.instagramclone.databinding.ActivitySignUpBinding
import com.example.instagramclone.models.User
import com.example.instagramclone.util.USER_NODE
import com.example.instagramclone.util.USER_PROFILE_FOLDER
import com.example.instagramclone.util.uploadImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    private var user = User()
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()){
        uri->
        uri?.let {
            uploadImage(uri, USER_PROFILE_FOLDER){
                if(it==null){

                }else{
                    user.image = it
                    binding.profileImage.setImageURI(uri)
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.SignUpButton.setOnClickListener {
            if((binding.name.editText?.text.toString() == "") or
                (binding.email.editText?.text.toString() == "") or
                (binding.password.editText?.text.toString() == "")
            ){
                Toast.makeText(this@SignUpActivity,"Please fill the all information", Toast.LENGTH_SHORT).show()
            }else{
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.email.editText?.text.toString(),
                    binding.password.editText?.text.toString()
                ).addOnCompleteListener {
                    result->
                    if(result.isSuccessful){
                        user.apply {
                            name = binding.name.editText?.text.toString()
                            email = binding.email.editText?.text.toString()
                            password = binding.password.editText?.text.toString()
                        }
                        Firebase.firestore.collection(USER_NODE)
                            .document(Firebase.auth.currentUser!!.uid).set(user)
                            .addOnSuccessListener {
                                Toast.makeText(this@SignUpActivity, "Login Successfully", Toast.LENGTH_SHORT).show()
                            }
                    }else{
                        Toast.makeText(this@SignUpActivity, result.exception?.localizedMessage, Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }
        binding.addImage.setOnClickListener {
            launcher.launch("image/*")
        }
    }
}
package ir.sajjadasadi.loginapp.View

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import ir.sajjadasadi.loginapp.databinding.ActivityMainBinding
import ir.sajjadasadi.loginapp.remote.RetrofitService
import ir.sajjadasadi.loginapp.remote.dataModel.DefaultModel
import ir.sajjadasadi.loginapp.remote.ext.ErrorUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewMainActivity(
    contextInstance: Context
) : FrameLayout(contextInstance) {
    val binding = ActivityMainBinding.inflate(LayoutInflater.from(context))

    fun onClickHandler(androidid: String) {
        binding.btnSend.setOnClickListener {
            val email = binding.edtInputEmail.text.toString()
            if (email.isEmpty()) {
                binding.textInputEmail.error = "ٍٍٍٍEmail is Empty"
                return@setOnClickListener
            } else {
                binding.textInputEmail.error = null
            }
            sendCodeInEmail(email)
            binding.btnSend.visibility = INVISIBLE
            binding.textInputEmail.visibility = INVISIBLE

            binding.txtSendEmail.visibility = VISIBLE
            binding.textInputCode.visibility = VISIBLE
            binding.btnConfirm.visibility = VISIBLE
            binding.txtWrong.visibility = VISIBLE
            binding.txtSendEmail.text =
                "Sended Email to: $email Please Enter Verify Code To Bellow Input"
        }
        binding.txtWrong.setOnClickListener {
            binding.btnSend.visibility = VISIBLE
            binding.textInputEmail.visibility = VISIBLE

            binding.txtSendEmail.visibility = INVISIBLE
            binding.textInputCode.visibility = INVISIBLE
            binding.btnConfirm.visibility = INVISIBLE
            binding.txtWrong.visibility = INVISIBLE
            binding.txtSendEmail.text = ""
        }

        binding.btnConfirm.setOnClickListener {
            val code = binding.edtCode.text.toString()
            val email = binding.edtInputEmail.text.toString()
            if (code.length < 6) {
                binding.textInputCode.error = "ERROR!"
                return@setOnClickListener
            } else {
                binding.textInputCode.error = null
            }
            verifyCodeInEmail(androidid, code, email)


        }
    }

    private fun sendCodeInEmail(email: String) {
        val service = RetrofitService.apiService

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.sendCode(email)
                if (response.isSuccessful) {
                    launch(Dispatchers.Main) {
                        val data = response.body() as DefaultModel
                        Toast.makeText(context, data.message, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    launch(Dispatchers.Main) {
                        Toast.makeText(
                            context,
                            ErrorUtils.getError(response),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                Log.i("SERVER ERROR", e.message.toString())
            }

        }
    }

    private fun verifyCodeInEmail(androidid: String, code: String, email: String) {
        val service = RetrofitService.apiService

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.verifyCode(androidid, code, email)
                if (response.isSuccessful) {
                    launch(Dispatchers.Main) {
                        val data = response.body() as DefaultModel
                        Toast.makeText(context, data.message, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    launch(Dispatchers.Main) {
                        Toast.makeText(
                            context,
                            ErrorUtils.getError(response),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                Log.i("SERVER ERROR", e.message.toString())
            }

        }
    }
}
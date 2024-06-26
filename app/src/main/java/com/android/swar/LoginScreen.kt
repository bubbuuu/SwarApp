package com.android.swar


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.swar.presentation.sign_in.SignInState
import com.google.firebase.Firebase
import com.google.firebase.database.database

@Composable
fun LoginScreen(modifier: Modifier = Modifier, typography: Typography,
                state: SignInState,
                onSignInClick: () -> Unit,
                viewModel: AuthViewModel, navController: NavController
) {
        val context = LocalContext.current
        LaunchedEffect(key1 = state.signInError) {
                state.signInError?.let { error ->
                        Toast.makeText(
                                context,
                                error,
                                Toast.LENGTH_LONG
                        ).show()
                }
        }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
                Text(
                        text = "Swar",
                        style = typography.bodyLarge,
                        modifier = Modifier.padding(top = 48.dp, bottom = 32.dp)
                )
                OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier.padding(16.dp)
                )

                OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        modifier = Modifier.padding(16.dp)
                )

                Button(
                        onClick = {
                                viewModel.login(email, password)
                        },
                        modifier = Modifier.padding(16.dp)
                ) {
                        Text(text = "Login")
                }
                Text(
                        text = "First Time Here? Register",
                        color = Color.Blue,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .clickable { navController.navigate("register") }
                )
                Button(onClick = onSignInClick) {
                        Text(text = "Sign in")
                }
        }

}
fun logi(navController: NavController){
        navController.navigate("register")
}




fun sendData() {
        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")

}
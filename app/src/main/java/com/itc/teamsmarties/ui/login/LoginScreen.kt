package com.itc.teamsmarties.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.view.KeyEvent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.itc.teamsmarties.R
import com.itc.teamsmarties.ui.activity.LoginActivity
import com.itc.teamsmarties.ui.activity.MainActivity
import com.itc.teamsmarties.ui.activity.SignupActivity

@SuppressLint("RememberReturnType")
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel()
) {
    // Use remember to keep track of focus requesters
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }

    //get current context
    val context = LocalContext.current

    // Observe isLoginSuccess using LaunchedEffect
    LaunchedEffect(viewModel.isLoginSuccess.value) {
        if (viewModel.isLoginSuccess.value) {
            Toast.makeText(
                context,
                "${viewModel.email.value} : Login Success",
                Toast.LENGTH_SHORT
            ).show()
            context.startActivity(Intent(context, MainActivity::class.java))
            (context as LoginActivity).finish()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(10f)
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.reddit_splash),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Curate interests in one convenient spot",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
                modifier = Modifier
                    .fillMaxWidth(0.8f),
            )
            Spacer(modifier = Modifier.height(100.dp))
            Text(
                text = "Sign In",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .fillMaxWidth(0.8f),
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = viewModel.email.value,
                onValueChange = { newValue ->
                    if (newValue.contains("\n")) {
                        viewModel.email.value = newValue.replace("\n", "")
                    } else {
                        viewModel.email.value = newValue
                    }
                    viewModel.validateEmail()
                },
                shape = RoundedCornerShape(12.dp),
                isError = viewModel.isEmailValid.value,
                placeholder = { Text(text = "Enter Email Address", color = MaterialTheme.colorScheme.onPrimary) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    textColor = MaterialTheme.colorScheme.onPrimary,
                    cursorColor = MaterialTheme.colorScheme.onPrimary,
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .onKeyEvent {
                        if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_TAB ||
                            (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER)
                        ) {
                            // Request focus on the next field (password)
                            passwordFocusRequester.requestFocus()
                            true // Consume the event
                        } else {
                            false // Don't consume the event
                        }
                    }
                    .focusRequester(emailFocusRequester)// Apply focusRequester
                    .testTag("email")
            )
            Text(
                modifier = Modifier.padding(start = 8.dp).testTag("email_error"),
                text = viewModel.emailErrMsg.value,
                fontSize = 14.sp,
                color = Color.Red
            )
            Spacer(modifier = Modifier.height(8.dp))

            var passwordVisibility by remember { mutableStateOf(false) }

            OutlinedTextField(
                value = viewModel.password.value,
                onValueChange = { newValue ->

                    if (newValue.contains("\n")) {
                        viewModel.password.value = newValue.replace("\n", "")
                    } else {
                        viewModel.password.value = newValue
                    }

                    viewModel.validatePassword()
                },
                isError = viewModel.isPasswordValid.value,
                shape = RoundedCornerShape(12.dp),
                placeholder = { Text(text = "Enter Password", color = MaterialTheme.colorScheme.onPrimary) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    textColor = MaterialTheme.colorScheme.onPrimary,
                    cursorColor = MaterialTheme.colorScheme.onPrimary,
                ),
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val visibilityIcon =
                        if (passwordVisibility) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(
                            imageVector = visibilityIcon,
                            contentDescription = "Toggle password visibility",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .focusRequester(passwordFocusRequester) // Apply focusRequester to the field
                    .testTag("password")
            )
            Text(
                modifier = Modifier.padding(start = 8.dp)
                    .testTag("password_error"),
                text = viewModel.passwordErrMsg.value,
                fontSize = 14.sp,
                color = Color.Red
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    viewModel.login()
                },
                modifier = Modifier
                    .height(40.dp)
                    .width(150.dp)
                    .testTag("Login"),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colorScheme.onPrimary,
                    disabledBackgroundColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                ),
                enabled = viewModel.isEnabledLoginButton.value
            ) {
                Text(text = "CONFRIM", color = MaterialTheme.colorScheme.primary)
            }
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = viewModel.loginErrorMessage.value,
                fontSize = 14.sp,
                color = Color.Red
            )
            Spacer(modifier = Modifier.height(6.dp))
            TextButton(
                onClick = {
                    val intent = Intent(context, SignupActivity::class.java)
                    context.startActivity(intent)
                    (context as LoginActivity).finish()
                },
                modifier = Modifier.fillMaxWidth(),

                ) {
                    Text(
                        text = "New User? ",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = "Signup Here",
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold
                    )
            }
        }
    }

}





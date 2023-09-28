package com.itc.teamsmarties.ui.signup

import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavController
import com.itc.teamsmarties.R
import com.itc.teamsmarties.ui.activity.LoginActivity
import com.itc.teamsmarties.ui.activity.MainActivity
import com.itc.teamsmarties.ui.activity.SignupActivity

@Composable
fun SignupScreen(
    navController: NavController, viewModel: SignupViewModel = viewModel()
) {
    //get current context
    val context = LocalContext.current

    val usernameFocusRequester = remember { FocusRequester() }
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val confirmPasswordFocusRequester = remember { FocusRequester() }

    val navigateToLogin: (context: Context) -> Unit = { context ->
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
        (context as SignupActivity).finish()
    }

    LaunchedEffect(viewModel.isSignUpSuccess.value) {
        if (viewModel.isSignUpSuccess.value) {
            Toast.makeText(
                context,
                "${viewModel.userName.value} : SignUp Success , Please SignIn ",
                Toast.LENGTH_SHORT
            ).show()
            context.startActivity(Intent(context, MainActivity::class.java))
            (context as SignupActivity).finish()
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
                    .height(70.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Welcome",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
                modifier = Modifier
                    .fillMaxWidth(0.8f),
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Sign Up",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .fillMaxWidth(0.8f),
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = viewModel.userName.value,
                onValueChange = { newValue ->
                    if (newValue.contains("\n")) {
                        viewModel.userName.value = newValue.replace("\n", "")
                    } else {
                        viewModel.userName.value = newValue
                    }
                    viewModel.validateUserName()
                },
                shape = RoundedCornerShape(12.dp),
                isError = viewModel.isUserNameValid.value,
                placeholder = { Text(text = "User Name", color = MaterialTheme.colorScheme.onPrimary) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    textColor = MaterialTheme.colorScheme.onPrimary,
                    cursorColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .onKeyEvent {
                        if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_TAB ||
                            (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER)
                        ) {
                            emailFocusRequester.requestFocus()
                            true
                        } else {
                            false
                        }
                    }
                    .focusRequester(usernameFocusRequester)
                    .testTag("User Name")
            )
            Text(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .testTag("username_error"),
                text = viewModel.userNameErrMsg.value,
                fontSize = 10.sp,
                color = Color.Red
            )
            Spacer(modifier = Modifier.height(8.dp))
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
                placeholder = { Text(text = "Email address", color = MaterialTheme.colorScheme.onPrimary) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    textColor = MaterialTheme.colorScheme.onPrimary,
                    cursorColor = MaterialTheme.colorScheme.onPrimary
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
                    .focusRequester(emailFocusRequester)
                    .testTag("email")
            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = viewModel.emailErrMsg.value,
                fontSize = 10.sp,
                color = Color.Red
            )
            Spacer(modifier = Modifier.height(6.dp))

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
                shape = RoundedCornerShape(12.dp),
                isError = viewModel.isPasswordValid.value,
                placeholder = { Text(text = "Password", color = MaterialTheme.colorScheme.onPrimary) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    textColor = MaterialTheme.colorScheme.onPrimary,
                    cursorColor = MaterialTheme.colorScheme.onPrimary
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
                    .onKeyEvent {
                        if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_TAB ||
                            (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER)
                        ) {
                            confirmPasswordFocusRequester.requestFocus()
                            true
                        } else {
                            false
                        }
                    }
                    .focusRequester(passwordFocusRequester)
                    .testTag("password")
            )
            Text(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .testTag("password_error"),
                text = viewModel.passwordErrMsg.value,
                fontSize = 10.sp,
                color = Color.Red
            )
            Spacer(modifier = Modifier.height(8.dp))

            var confirmPasswordVisibility by remember { mutableStateOf(false) }

            OutlinedTextField(
                value = viewModel.confirmPassword.value,
                onValueChange = { newValue ->
                    if (newValue.contains("\n")) {
                        viewModel.confirmPassword.value = newValue.replace("\n", "")
                    } else {
                        viewModel.confirmPassword.value = newValue
                    }
                    viewModel.validateConfirmPassword()
                },
                shape = RoundedCornerShape(12.dp),
                isError = viewModel.isConfirmPasswordValid.value,
                placeholder = { Text(text = "Confirm Password", color = MaterialTheme.colorScheme.onPrimary) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    textColor = MaterialTheme.colorScheme.onPrimary,
                    cursorColor = MaterialTheme.colorScheme.onPrimary
                ),
                visualTransformation = if (confirmPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val visibilityIcon =
                        if (confirmPasswordVisibility) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                    IconButton(onClick = {
                        confirmPasswordVisibility = !confirmPasswordVisibility
                    }) {
                        Icon(
                            imageVector = visibilityIcon,
                            contentDescription = "Toggle password visibility",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .focusRequester(confirmPasswordFocusRequester)
                    .testTag("confirm password")
            )
            Text(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .testTag("confirm password_error"),
                text = viewModel.confPasswordErrMsg.value,
                fontSize = 10.sp,
                color = Color.Red
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = {
                    viewModel.SignUp()
                },
                modifier = Modifier
                    .height(40.dp)
                    .width(150.dp)
                    .testTag("confirm"),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colorScheme.onPrimary,
                    disabledBackgroundColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                ),
                enabled = viewModel.isEnabledRegisterButton.value
            ) {
                Text(text = "CONFRIM", color = MaterialTheme.colorScheme.primary)
            }
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = viewModel.signUpErrorMessage.value,
                fontSize = 10.sp,
                color = Color.Red
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextButton(
                onClick = {
                    navigateToLogin(context)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Row() {
                    Text(
                        text = "Already have an account?  ",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = "Sign in",
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }
    }
}


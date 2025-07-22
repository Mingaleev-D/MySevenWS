package com.example.seven_w_s_tt.ui.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.seven_w_s_tt.ui.componets.CenterAlignedTopAppBarComponent
import com.example.seven_w_s_tt.ui.navigation.Screen
import com.example.seven_w_s_tt.ui.theme.BtnColor
import com.example.seven_w_s_tt.ui.theme.TextColor
import com.example.seven_w_s_tt.utils.SimpleResponse
import com.example.seven_w_s_tt.utils.onError
import com.example.seven_w_s_tt.utils.onSuccess

@Composable
fun RegisterScreen(
     navController: NavController,
     viewModel: AuthViewModel,
) {
     val context = LocalContext.current
     val authResult by viewModel.authResult.collectAsState(initial = null)


     LaunchedEffect(key1 = authResult) {
          authResult?.onSuccess { message ->
               // Toast.makeText(context, message, Toast.LENGTH_LONG).show()
               navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Register.route) { inclusive = true }
               }
          }?.onError { message ->
               Toast.makeText(context, message, Toast.LENGTH_LONG).show()
          }
     }

     Scaffold(
          modifier = Modifier
               .fillMaxSize(),
          containerColor = Color.White,
          topBar = {
               CenterAlignedTopAppBarComponent(
                    tittle = "Регистрация",
                    onClick = { navController.popBackStack() }
               )
          }
     ) { paddingValue ->
          Column(
               modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
                    .padding(paddingValue),
               horizontalAlignment = Alignment.CenterHorizontally
          ) {
               Spacer(modifier = Modifier.height(130.dp))

               // Поле E-mail
               Text(
                    text = "e-mail",
                    fontSize = 15.sp,
                    color = TextColor,
                    modifier = Modifier
                         .fillMaxWidth()
                         .padding(bottom = 4.dp)
               )
               OutlinedTextField(
                    value = viewModel.email,
                    onValueChange = { viewModel.onEmailChanged(it) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                         focusedBorderColor = TextColor,
                         unfocusedBorderColor = TextColor,
                         cursorColor = TextColor
                    ),
                    singleLine = true,
                    placeholder = {
                         Text(
                              "example@example.ru",
                              color = Color(0xFFC0A487)
                         )
                    }
               )
               Spacer(modifier = Modifier.height(24.dp))

               // Поле Пароль
               Text(
                    text = "Пароль",
                    fontSize = 15.sp,
                    color = TextColor,
                    modifier = Modifier
                         .fillMaxWidth()
                         .padding(bottom = 4.dp)
               )
               OutlinedTextField(
                    value = viewModel.password,
                    onValueChange = { viewModel.onPasswordChanged(it) },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    shape = RoundedCornerShape(24.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                         focusedBorderColor = TextColor,
                         unfocusedBorderColor = TextColor,
                         cursorColor = TextColor,
                    ),
                    singleLine = true,
                    placeholder = { Text("******", color = Color(0xFFC0A487)) }
               )
               Spacer(modifier = Modifier.height(24.dp))

               // Поле Повторите пароль
               Text(
                    text = "Повторите пароль",
                    fontSize = 15.sp,
                    color = TextColor,
                    modifier = Modifier
                         .fillMaxWidth()
                         .padding(bottom = 4.dp)
               )
               OutlinedTextField(
                    value = viewModel.confirmPassword,
                    onValueChange = { viewModel.onConfirmPasswordChanged(it) },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    shape = RoundedCornerShape(24.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                         focusedBorderColor = TextColor,
                         unfocusedBorderColor = TextColor,
                         cursorColor = TextColor,
                    ),
                    singleLine = true,
                    placeholder = { Text("******", color = Color(0xFFC0A487)) }
               )

               Spacer(modifier = Modifier.height(40.dp))

               // Кнопка Регистрация
               Button(
                    onClick = {
                         if (viewModel.password == viewModel.confirmPassword) {
                              viewModel.register()
                         } else {
                              Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_SHORT)
                                   .show()
                         }
                    },
                    enabled = authResult !is SimpleResponse.Loading,
                    modifier = Modifier
                         .fillMaxWidth()
                         .height(56.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                         containerColor = BtnColor,
                         contentColor = Color.White //
                    )
               ) {
                    if (authResult is SimpleResponse.Loading) {
                         CircularProgressIndicator(
                              color = MaterialTheme.colorScheme.onPrimary,
                              modifier = Modifier.size(24.dp)
                         )
                    } else {
                         Text("Регистрация", fontSize = 18.sp)
                    }
               }
          }
     }
}

package com.example.buildone.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buildone.ui.theme.GrayColor
import com.example.buildone.ui.theme.PurpleGrey40
import com.example.buildone.ui.theme.TextColor
import com.example.buildone.ui.theme.WhiteColor
import com.example.buildone.ui.theme.custom
import com.example.buildone.ui.theme.custom1

@Composable
fun NormalTextComponent(text: String,
                        font: Int,
                        fontWeight: FontWeight) {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = text,
        style = TextStyle(
            fontSize = font.sp,
            fontWeight = fontWeight,
            fontStyle = FontStyle.Normal
        ),
        color = TextColor,
        textAlign = TextAlign.Center
    )
}

@Composable
fun TextInputs(label: String,
               leadingIcon: ImageVector,
               onTextSelected: (String) -> Unit,
               errorStatus : Boolean = false) {

    var textValue by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(modifier = Modifier.fillMaxWidth()
        .pointerInput(Unit) { // Add touch detection to clear focus when clicking outside
            detectTapGestures(onTap = { focusManager.clearFocus() })
        },
        label = { Text(text = label) },
        value = textValue,
        onValueChange = { textValue = it
                        onTextSelected(it )},
        colors = TextFieldDefaults.colors(
            focusedContainerColor = custom1,
            unfocusedContainerColor = WhiteColor,
            focusedLabelColor = PurpleGrey40,
            cursorColor = GrayColor,
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine =true,
        maxLines = 1,
        leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = null) },
        isError = !errorStatus
    )
}

@Composable
fun PasswordTextInputs(label: String,
                       leadingIcon: ImageVector,
                       onTextSelected: (String) -> Unit,
                       errorStatus : Boolean = false) {

    var password by remember { mutableStateOf("") }
    var passVisible by remember { mutableStateOf(false) }
    val localFocusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = label) },
        value = password,
        onValueChange = { password = it
                        onTextSelected(it)},
        colors = TextFieldDefaults.colors(
            focusedContainerColor = custom1,
            unfocusedContainerColor = WhiteColor,
            focusedLabelColor = PurpleGrey40,
            cursorColor = GrayColor,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ), singleLine = true,
        keyboardActions = KeyboardActions(onDone = {localFocusManager.clearFocus()}),
        leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = null) },
        trailingIcon = {
            val iconImage = if (passVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            val description = if (passVisible) "Hide password" else "Show password"

                IconButton(onClick = { passVisible = !passVisible }) {
                    Icon(imageVector = iconImage, contentDescription = description)
                }
            },
            visualTransformation = if (passVisible) VisualTransformation.None else PasswordVisualTransformation()
        , isError = !errorStatus
        )
    }

@Composable
fun CheckBox(onCheckedChange : (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        var checked by remember { mutableStateOf(false) }
        Checkbox(checked = checked, onCheckedChange = { checked = !checked
        onCheckedChange.invoke(it)})
    }
}

@Composable
fun ClickableTextComponent(value: String,
                           linkText: String,
                           hyperlinks: String
) {

    val annotatedString = buildAnnotatedString {
        append(value)
        val startIndex = value.indexOf(linkText)
        val endIndex = startIndex + linkText.length
        addStyle(
            style = SpanStyle(
                textDecoration = TextDecoration.Underline
            ),
            start = startIndex,
            end = endIndex
        )
        addStringAnnotation(
            tag = "URL",
            annotation = hyperlinks,
            start = startIndex,
            end = endIndex
        )
    }
    val urlHandler = LocalUriHandler.current

    ClickableText(
        text = annotatedString,
        onClick = {
            annotatedString.getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    urlHandler.openUri(stringAnnotation.item)
                }
        }
    )
}

@Composable
fun ButtonComponent(value: String,
                    onButtonClicked: () -> Unit,
                    isEnabled: Boolean,
                    function: () -> Unit
) {
    Button(
        onClick = {
            onButtonClicked.invoke()
        }, modifier = Modifier
            .fillMaxWidth()
            .shadow(5.dp, shape = RoundedCornerShape(15.dp)),
        enabled = isEnabled
    ) {
        Text(text = value, fontSize = 25.sp)
    }
}

@Composable
fun DividerTextComponent() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .height(1.dp),
            color = Color.Black
        )
        Text(
            text = "or",
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .height(1.dp),
            color = Color.Black
        )
    }
}

@Composable
fun ClickableTextLoginComponent(onNavigate: () -> Unit,
                                value: String,
                                linkText: String
) {
    val annotatedString = buildAnnotatedString {
        append(value)
        val startIndex = value.indexOf(linkText)
        if (startIndex != -1) {
            val endIndex = startIndex + linkText.length
            addStyle(
                style = SpanStyle(
                    textDecoration = TextDecoration.Underline,
                    color = custom
                ),
                start = startIndex,
                end = endIndex
            )
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(),
        text = annotatedString,
        style = TextStyle(fontSize = 20.sp),
        onClick = { offset ->
            val startIndex = value.indexOf(linkText)
            val endIndex = startIndex + linkText.length
            if (offset in startIndex until endIndex) {
                onNavigate()
            }
        }
    )
}
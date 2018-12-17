package com.coding.objectrepository;

import org.openqa.selenium.By;

public class SignInOR {
	public static final By userLink = By.id("userAccountLink");
	public static final By link_signIn = By.id("SignIn");
	public static final By btn_signIn = By.cssSelector("button#signInButton");
	public static final By error_message = By.cssSelector("div#errors1>span");
	public static final By btn_close = By.cssSelector("div#ModalFrame>div>a");
}

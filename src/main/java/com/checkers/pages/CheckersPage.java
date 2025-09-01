package com.checkers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckersPage {
	private WebDriver driver;
	private List<WebElement> orangePieces;

	public CheckersPage(WebDriver driver) {
		this.driver = driver;
	}

	public void loadGameURL() {
		driver.get("https://www.gamesforthebrain.com/game/checkers/");
	}

	public boolean isSiteUp() {
		return driver.getTitle().contains("Checkers");
	}

	public List<WebElement> getBoardSquares() {
		return driver.findElements(By.xpath("//div[@id='board']//img"));
	}

	public void clickSquare(int index) {
		getBoardSquares().get(index).click();
	}

	public void makeMove() {
		driver.findElement(By.xpath("//p[contains(text(),'Make a move.')]")).click();
	}

	public void clickOnRestartGame() {
		driver.findElement(By.xpath("//a[contains(text(),'Restart...')]")).click();
	}

	public void waitForBoardReload() {
		new WebDriverWait(driver, Duration.ofSeconds(5))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='board']//img")));
	}

	public boolean isRestartSuccessful() {

		boolean flag = false;
		clickOnRestartGame();
		orangePieces = new WebDriverWait(driver, Duration.ofSeconds(5))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='board']//img")));
		int size = orangePieces.size();
		if(size > 0) {
			flag = true;
		}
		return flag;

	}
}

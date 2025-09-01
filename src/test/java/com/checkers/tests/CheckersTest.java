package com.checkers.tests;

import com.checkers.pages.CheckersPage;
import com.checkers.utils.utils.DriverFactory;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class CheckersTest {
	private WebDriver driver;
	private com.checkers.pages.CheckersPage game;

	@BeforeClass
	public void setup() {
		driver = com.checkers.utils.utils.DriverFactory.getDriver();
		game = new CheckersPage(driver);
	}

	@Test(priority = 1)
	public void testSiteIsUp() {
		game.loadGameURL();

		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='board']//img")));

		List<WebElement> squares = driver.findElements(By.xpath("//div[@id='board']//img"));
		System.out.println("Total squares found: " + squares.size());

		for (int i = 0; i < squares.size(); i++) {
			String src = squares.get(i).getAttribute("src");
			System.out.println("Square " + i + ": " + src);
		}

	}

	@Test(priority = 2)
	public void testMakeFiveMovesIncludingCapture() throws InterruptedException {
		int moveCount = 0;
		boolean captureDone = false;

		while (moveCount < 5) {
			List<WebElement> squares = driver.findElements(By.xpath("//div[@id='board']//img"));
			boolean moveMade = false;

			for (int i = 0; i < squares.size(); i++) {
				String src = squares.get(i).getAttribute("src");

				if (src.endsWith("you1.gif")) { // Orange piece
					int row = i / 8;
					int col = i % 8;

					// Try capture moves: (row - 2, col ± 2)
					for (int dCol : new int[] { -2, 2 }) {
						int newRow = row - 2;
						int newCol = col + dCol;
						int midRow = row - 1;
						int midCol = col + dCol / 2;

						if (newRow >= 0 && newCol >= 0 && newCol < 8 && midCol >= 0 && midCol < 8) {
							int midIndex = midRow * 8 + midCol;
							int targetIndex = newRow * 8 + newCol;

							if (targetIndex >= 0 && targetIndex < 64) {
								String midSrc = squares.get(midIndex).getAttribute("src");
								String targetSrc = squares.get(targetIndex).getAttribute("src");

								if (midSrc.endsWith("me1.gif") && targetSrc.endsWith("gray.gif")) {
									squares.get(i).click();
									Thread.sleep(500);
									squares.get(targetIndex).click();
									Thread.sleep(1000);
									driver.findElement(By.linkText("Make a move")).click();
									Thread.sleep(1500);
									moveCount++;
									captureDone = true;
									moveMade = true;
									break;
								}
							}
						}
					}

					if (moveMade)
						break;

					// Try regular moves: (row - 1, col ± 1)
					for (int dCol : new int[] { -1, 1 }) {
						int newRow = row - 1;
						int newCol = col + dCol;

						if (newRow >= 0 && newCol >= 0 && newCol < 8) {
							int targetIndex = newRow * 8 + newCol;
							if (targetIndex >= 0 && targetIndex < 64) {
								String targetSrc = squares.get(targetIndex).getAttribute("src");
								if (targetSrc.endsWith("gray.gif")) {
									squares.get(i).click();
									Thread.sleep(500);
									squares.get(targetIndex).click();
									Thread.sleep(1000);
									game.makeMove();
									new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
											.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='board']//img")));
									Thread.sleep(1500);
									moveCount++;
									moveMade = true;
									break;
								}
							}
						}
					}
				}

				if (moveMade)
					break;
			}

			if (!moveMade) {
				System.out.println("No legal moves found.");
				break;
			}
		}

		Assert.assertEquals(moveCount, 5, "Exactly 5 moves should be made.");
		System.out.println("Exactly 5 moves should be made...!!!");
	}

	@Test(priority = 3)
	public void testRestartGame() throws InterruptedException {

		boolean restartSuccessful = game.isRestartSuccessful();
		Assert.assertTrue(restartSuccessful, "Game did not restart successfully");

	}

	@AfterClass
	public void tearDown() {
		DriverFactory.quitDriver();
	}
}
package org.testerselenium.intermedio.video;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class VideoRecorder {
	private ScreenRecorder screenRecorder;
	String chromePath =  System.getProperty("user.dir") + "/drivers/chromedriver.exe";
	WebDriver driver;
	
	private void stopRecording() throws IOException {
		this.screenRecorder.stop();
	}
	
	private void startRecording(String videoPath) throws IOException, AWTException {
		File file = new File(videoPath);
		Dimension screenSize =  Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		
		Rectangle captureSize = new Rectangle(0,0,width,height);
		
		GraphicsConfiguration gc =  GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		
		this.screenRecorder = new SpecializedScreenRecorder(gc, captureSize, 
				new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI), 
				new Format(MediaTypeKey, MediaType.VIDEO,EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
						CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey, Rational.valueOf(15),
						QualityKey, 1.0f, KeyFrameIntervalKey, 15*60), 
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)), 
				null, file, "ScreenRecorded");
		
		this.screenRecorder.start();
	}
	
	@Test
	public void videoTest() throws IOException, AWTException {
		VideoRecorder vr = new VideoRecorder();
		System.setProperty("webdriver.chrome.driver", chromePath);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		vr.startRecording(System.getProperty("user.dir") + "/video/");
		
		driver.get("http://www.google.com");
		
		WebElement txtSearch = driver.findElement(By.name("q"));
		txtSearch.sendKeys("Tester Fabrik");
		txtSearch.submit();
		System.out.println("Page Title is: " + driver.getTitle());
		driver.quit();
		vr.stopRecording();
	}
	

}

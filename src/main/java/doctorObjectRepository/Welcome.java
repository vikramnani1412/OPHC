package doctorObjectRepository;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Welcome {

	// ============================
	// Locators (@FindBy)
	// ============================

	@FindBy(xpath = "//img[@class='profile-img']")
	private WebElement profileImg;

	@FindBy(xpath = "//span[contains(normalize-space(.), 'View All')]")
	private WebElement consultationsViewAllLnk;

	@FindBy(xpath = "//button[.='▶']")
	private WebElement nextMonthBtn;

	@FindBy(xpath = "//button[.='Set Availability']")
	private WebElement setAvailabilityBtn;

	@FindBy(xpath = "//span[contains(normalize-space(.), 'Add Slot')]")
	private WebElement addSlotBtn;

	@FindBy(xpath = "(//div[@class='day disabled ng-star-inserted'])[last()]")
	private WebElement lastDisabledDayInTheMonthEle;

	@FindBy(xpath = "//div[@class='day ng-star-inserted active today']")
	private WebElement todaysDateInTheMonthEle;

	// NOTE: this locator does NOT reliably target "tomorrow". It targets the
	// first div.day cell in document order, which depends on calendar layout
	// (e.g. leading days from the previous month can share this class).
	// A relative-to-today locator (e.g. today's cell's following sibling)
	// is more reliable, but requires knowing the actual rendered DOM
	// structure around the "today" cell to write correctly.
	@FindBy(xpath = "(//div[@class='day ng-star-inserted'])[1]")
	private WebElement tomorrowDateEle;

	@FindBy(xpath = "//i[@class='fa-solid fa-xmark delete-icon ng-star-inserted']")
	private WebElement slotDeleteIcon;

	@FindBy(xpath = "//button[.='Cancel']")
	private WebElement removeSlotCancelBtn;

	@FindBy(xpath = "//button[.='Yes, Remove']")
	private WebElement yesRemoveBtn;

	@FindBy(xpath = "(//h5[.='Upcoming Consultations']/../following-sibling::div/div/div)")
	private WebElement totalUpcomingSlots;

	@FindBy(xpath = "//div[@class='slot booked-slot']")
	private WebElement totalBookedSlots;

	@FindBy(xpath = "//div[@class='slot']")
	private WebElement totalAvailableSlots;

	// NOTE: hardcoded to '16:30' - only matches a slot at that exact time.
	// Consider parameterizing this (see getJoinCallBtnForTime below) instead
	// of relying on a fixed time string baked into the locator.
	@FindBy(xpath = "//small[.='16:30']/../following-sibling::div//div[.=' Join Call ']")
	private WebElement accordingToCurrentTimeJoinCallBtn;

	// ============================
	// Constructor
	// ============================

	private final WebDriver driver;

	public Welcome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// ============================
	// Getters (raw element access, kept for flexibility)
	// ============================

	public WebElement getProfileImg() {
		return profileImg;
	}

	public WebElement getConsultationsViewAllLnk() {
		return consultationsViewAllLnk;
	}

	public WebElement getAddSlotBtn() {
		return addSlotBtn;
	}

	public WebElement getNextMonthBtn() {
		return nextMonthBtn;
	}

	public WebElement getSetAvailabilityBtn() {
		return setAvailabilityBtn;
	}

	public WebElement getLastDisabledDayInTheMonthEle() {
		return lastDisabledDayInTheMonthEle;
	}

	public WebElement getTodaysDateInTheMonthEle() {
		return todaysDateInTheMonthEle;
	}

	public WebElement getTomorrowDateEle() {
		return tomorrowDateEle;
	}

	public WebElement getSlotDeleteIcon() {
		return slotDeleteIcon;
	}

	public WebElement getRemoveSlotCancelBtn() {
		return removeSlotCancelBtn;
	}

	public WebElement getYesRemoveBtn() {
		return yesRemoveBtn;
	}

	public WebElement getTotalUpcomingSlots() {
		return totalUpcomingSlots;
	}

	public WebElement getTotalBookedSlots() {
		return totalBookedSlots;
	}

	public WebElement getTotalAvailableSlots() {
		return totalAvailableSlots;
	}

	public WebElement getAccordingToCurrentTimeJoinCallBtn() {
		return accordingToCurrentTimeJoinCallBtn;
	}

	// ============================
	// Business Library
	// (Reusable, intention-revealing actions that combine waits + steps,
	//  so test classes don't need to know low-level element details.)
	// ============================

	/**
	 * Clicks the profile icon to open the profile menu.
	 */
	public void clickOnProfileIcon() {
		waitForClickable(profileImg);
		profileImg.click();
	}

	/**
	 * Navigates to the "View All" consultations page.
	 */
	public void clickOnConsultationsViewAll() {
		waitForClickable(consultationsViewAllLnk);
		consultationsViewAllLnk.click();
	}

	/**
	 * Clicks "Set Availability" to begin adding a new slot.
	 */
	public void clickOnSetAvailability() {
		waitForClickable(setAvailabilityBtn);
		setAvailabilityBtn.click();
	}

	/**
	 * Clicks "+ Add Slot".
	 */
	public void clickOnAddSlot() {
		waitForClickable(addSlotBtn);
		addSlotBtn.click();
	}

	/**
	 * Advances the calendar view forward by the given number of months.
	 * @param numberOfMonths how many times to click the next-month arrow
	 */
	public void navigateToNextMonth(int numberOfMonths) {
		for (int i = 0; i < numberOfMonths; i++) {
			waitForClickable(nextMonthBtn);
			nextMonthBtn.click();
		}
	}

	/**
	 * Selects today's date cell on the currently displayed calendar month.
	 */
	public void selectTodaysDate() {
		waitForClickable(todaysDateInTheMonthEle);
		todaysDateInTheMonthEle.click();
	}

	/**
	 * Deletes a slot via its delete (x) icon, then confirms removal.
	 */
	public void deleteSlotAndConfirm() {
		waitForClickable(slotDeleteIcon);
		slotDeleteIcon.click();

		waitForClickable(yesRemoveBtn);
		yesRemoveBtn.click();
	}

	/**
	 * Deletes a slot via its delete (x) icon, then cancels the removal
	 * (used to verify the cancel flow does not actually delete the slot).
	 */
	public void deleteSlotAndCancel() {
		waitForClickable(slotDeleteIcon);
		slotDeleteIcon.click();

		waitForClickable(removeSlotCancelBtn);
		removeSlotCancelBtn.click();
	}

	/**
	 * Returns the total number of upcoming consultations shown on the
	 * welcome page, as an int.
	 * @return upcoming consultations count
	 */
	public int getUpcomingConsultationsCount() {
		waitForVisible(totalUpcomingSlots);
		String text = totalUpcomingSlots.getText().trim();
		return text.isEmpty() ? 0 : Integer.parseInt(text);
	}

	/**
	 * Returns true if a "Join Call" button is available for a slot at the
	 * given time label (e.g. "16:30"). Builds the locator dynamically
	 * instead of relying on a hardcoded time baked into @FindBy.
	 * @param timeLabel the slot's displayed time, e.g. "16:30"
	 * @return true if a matching Join Call button is present and displayed
	 */
	public boolean isJoinCallAvailableForTime(String timeLabel) {
		try {
			WebElement joinCallBtn = driver.findElement(
					org.openqa.selenium.By.xpath(
							"//small[.='" + timeLabel + "']/../following-sibling::div//div[contains(normalize-space(.), 'Join Call')]"));
			return joinCallBtn.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	// ============================
	// Internal wait helpers
	// ============================

	private void waitForClickable(WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.elementToBeClickable(element));
	}

	private void waitForVisible(WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.visibilityOf(element));
	}
}

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver

fun main() {

    // Set this property, so it finds the exe to utilise. Source: https://chromedriver.storage.googleapis.com/index.html?path=84.0.4147.30/
    System.setProperty("webdriver.chrome.driver", Config.pathToChromeDriverExeLocation)

    // Create instance of chrome driver.
    val browser: WebDriver = ChromeDriver()

    // Login and navigate to edit page.
    loginToAnkiAndNavigateToEdit(browser)

    // Find reference to front field.
    val front : WebElement = browser.findElement(By.xpath("//*[@id=\"f0\"]"))

    // Find reference to back field.
    val back : WebElement = browser.findElement(By.xpath("//*[@id=\"f1\"]"))

    // Find reference to save button.
    val saveButton : WebElement = browser.findElement(By.xpath("//*[@class=\"btn btn-primary\"]"))

    // Add list of words to store. The first word is the front of the card, second is back. Front and back values are separated by a hyphen and each line must be ended with a '.' character.
    val listOfWords: String ="""
      το - the.
γράμμα  - letter.
Kακό - Bad.
Καλό - Good.
Μαμά - Mum.
μπαμπάς - Dad.
γεγονός - fact.
Και- and.
λουλούδι -  flower.
ζουζούνι - insect.
Χρώμα - colour/ paint.
ο ωκεανός - the ocean.
στάση- stop / station.
ψυχή - soul / psyche.
η φιλοσοφία  - the philosophy.
δάδα- torch.
ύμνος - hymn.
ξυλόφονο - xylophone.
ντουντούκα- megaphone.
βοuβάλι - buffalo.
Ο θρόνος - the throne.

    """.trimIndent()

    // Process list and store deck.
    listOfWords(front,back, saveButton,listOfWords)

    // Wait three seconds.
    Thread.sleep(3000)

    // Close the generated browser once finished.
    browser.close()
}

fun loginToAnkiAndNavigateToEdit(browser: WebDriver){

    // Navigate to website.
    browser["https://ankiweb.net/decks/"]

    // Find email field.
    browser.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(Config.email)

    // Find password field.
    browser.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(Config.password)

    // Find log in button.
    browser.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input")).submit()

    // Go to edit part:
    browser["https://ankiweb.net/edit/"]

    // Clear and Set deck name:
    browser.findElement(By.xpath("//*[@id=\"deck\"]")).clear()
    browser.findElement(By.xpath("//*[@id=\"deck\"]")).sendKeys(Config.deckName)
}

fun listOfWords(front: WebElement, back: WebElement, saveButton: WebElement, listOfWords: String)
{
    // Split by '.' character and by hyphen.
    val splittedStrings = listOfWords.trim().split(".","-")

    // Create list to store sanitised keys and values.
    val formattedStrings = mutableListOf<String>()

    // Loop through each word, remove any leading and trailing spaces and remove '.' characters.
    splittedStrings.forEach{
        formattedStrings.add(it.trim().replace(".",""))
    }

    // To make it explicitly easier, add keys and values to their own lists.
    val keys: MutableList<String> = mutableListOf()
    val values : MutableList <String> = mutableListOf()

    // Populate Keys and values list.
    for (index in 0.until(formattedStrings.size))
    {
        // If index is even, it is a key, else a value.
        if(index % 2 == 0)
        {
            keys.add(formattedStrings[index])
        }
        else
        {
            values.add(formattedStrings[index])
        }
    }

        for (index in 0.until(keys.size - 1))
    {
        // Set front value to key.
        front.sendKeys(keys[index])

        // Set back value to value.
        back.sendKeys(values[index])

        // Save this entry.
        saveButton.click()

        // Sleep, to give it enough time to process.
       Thread.sleep(1000)
    }
}

# TestVagrant hiring challenge for applicants 

About the codebase:
---------------------------------
*This is a simple test project for testing a few scenarios on a sample flight/hotel booking website. There are **3 tests** in total spread over 3 test classes. Following tech stack has been used to develop the same.*

**Tech Stack:** *Java*  *Selenium* *TestNG*  *Gradle/Maven*

Problem Statement
----------------------------------
**Tasks:**

1. Test are failing which needs your expertise to fix it.
2. Review and point out design issues with the current codebase/framework, if any.
3. Improve/refactor the code to implement your suggestions.

**What we want:**
1. Create a GitHub account if not existing already.
2. Fork this repo (DO NOT CLONE).
3. Fix the errors and refactor the code, consider **abstractions, reusability and maintenance.**
4. Make sure you make multiple check-ins in the process, we would love to see your progress bit by bit.
5. Also check-in a separate file where you should list all your code review comments.
6. Send us the link of your GitHub repo to **careers@testvagrant.com**. Also attach your **resume**.


** How is the problem solved:**
1. Created a multi-tier architecture by separating tests, page objects and utilities. Created an initialization package to connect between all
2. Used Page Factory approach at the framework level - object of a PO would be created only when it is to be used
3. Added resources folder to keep all important files other than source code intact and together
4. Configured data file for making scripts data-driven
5. Created packaging structure for the entire code
6. Applied abstraction - exposing only Initiator to test classes and access to all PO methods through a single object
7. Applied Reusability - created BasePage PO having all common methods to be used across all other POs, explicit wait also added in a single utility class used across all POs
8. Applied Maintainability - Code is structured into different packages with loggers and relevant method names making it easier for debugging in the longer run
9. Checked-in a file called Design_Issues_As_I_See.pdf in "Files" folder pointing out various design anomalies or approaches that could be done better 
10. Execute tests using mvn verify command on console to get HTML reports as well in target folder
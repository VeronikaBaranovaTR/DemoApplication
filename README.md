# DemoApplication

Novus extract files have been loaded to DEV. In order to load them to TEST via application:

Navigate to DemoApplication class --> run() method and insert in "File folder = new File("");//insert path for files to be loaded here" string the path to files to be loaded.

Navigate to application.properties file and change the spring.datasource.url to: jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=c474gppdkdbtf.int.thomsonreuters.com)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=dockets_tst_bkr.int.thomsonreuters.com)))

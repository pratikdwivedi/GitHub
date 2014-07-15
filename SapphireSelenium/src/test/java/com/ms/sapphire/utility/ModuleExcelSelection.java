package com.ms.sapphire.utility;

public class ModuleExcelSelection {
	public static String getExcelFilePath(int moduleType) {
		String filePath = null;
		switch (moduleType) {
		case 1:
			filePath = "SapphireFiles\\AutomationTestData.xls";
			break;
		case 2:
			filePath = "SapphireFiles\\PortfolioTestData.xls";
			break;
		case 3:
			filePath = "SapphireFiles\\WorkspaceTestData.xls";
			break;
		case 4:
			filePath = "SapphireFiles\\InboxTestData.xls";
			break;
		case 5:
			filePath = "SapphireFiles\\DashboardTestData.xls";
			break;
		case 6:
			filePath = "SapphireFiles\\ScoreTestData.xls";
			break;
		case 7:
			filePath= "SapphireFiles\\DeleteItemFiles\\AdminDeleteData.xls";
			break;
		case 8:
			filePath="SapphireFiles\\DeleteItemFiles\\PortfolioDeleteData.xls";
		case 9:
			filePath="SapphireFiles\\DeleteItemFiles\\WorkspaceDeleteData.xls";
		case 10:
			filePath="SapphireFiles\\Signal_Score_Algorithms_v1.0-Internal.xlsx";
		default:
			break;
		}
		return filePath;
	}
}

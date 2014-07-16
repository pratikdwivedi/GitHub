#!/bin/sh
#
# Copyright (C) 2012 Medsight Solutions Inc.
# author	: Sharad Prakash
# user updated	:
# date updated	: 23-Aug-2012
# Description	: This script creates the MS and Sapphire Schema
#####################################################################
FILE_NAME=/script/project.properties
echo "####################################################################"
echo "Welcome to the Medsight Solutions Sapphire Schema Creation Program"
echo "Copyright (c) 2012 Medsight solutions inc."
echo "Curent time: $(date)" 
echo "####################################################################"

# Key in Property File
dbUser="dbUser"
dbPassword="dbPassword"
dbHost="dbHost"
dbMS="dbMS"
dbSapphire="dbSapphire"
dbUdr="dbUdr"
dbCsetllogs="dbCsetllogs"
# Variable to hold the Property Value
prop_value=""
getProperty()
{
        prop_key=$1
        prop_value=`cat ${FILE_NAME} | grep ${prop_key} | cut -d'=' -f2`
}
getProperty ${dbUser}
echo "Key = ${dbUser} ; Value = " ${prop_value}
getProperty ${dbPassword}
echo "Enter database user id: "
echo $dbUser
echo "Enter database user's password: "
echo $dbPassword
echo "Enter database host name or IP address: "
echo $dbHost
echo "Enter database schema name for MS CORE (ex. ms): "
echo $dbMS
echo "Enter database schema name for Sapphire (ex. sapphire): "
echo $dbSapphire
echo "Enter database schema name for UDR (ex. udr): "
echo $dbUdr
echo "Enter database schema name for CS etl log (ex. ms_etl_logs): "
echo $dbCsetllogs
# Define variables
hashsep="#################################################################"
linesep="-----------------------------------------------------------------"
mysqlcmde="mysql -u$dbUser -p$dbPassword -h$dbHost "
# Test connection to the database
echo $linesep
echo "$(date): Testing connection to the database.."
testconnection=$(mysql -u $dbUser -p$dbPassword -h$dbHost -s -N <<QUERY_INPUT
    SELECT 1 FROM dual;
QUERY_INPUT
)
if [ $testconnection = 1 ]; then
	echo "Connection to database succeeded."
else
	echo "Connection to database failed. Check the user's credentials.."
	exit 1
fi
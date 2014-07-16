#!/bin/sh
#
# Copyright (C) 2012 Medsight Solutions Inc.
# author	: Sharad Prakash
# user updated	:
# date updated	: 23-Aug-2012
# Description	: This script creates the MS and Sapphire Schema
#####################################################################
FILE_NAME=./project.properties
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
#echo "Key = ${dbUser} ; Value = " ${prop_value}
username=${prop_value}
getProperty ${dbPassword}
#echo "Key = ${dbPassword} ; Value = " ${prop_value}
password=${prop_value}
getProperty ${dbHost}
#echo "Key = ${dbHost} ; Value = " ${prop_value}
host=${prop_value}
getProperty ${dbMS}
#echo "Key = ${dbMS} ; Value = " ${prop_value}
ms=${prop_value}
getProperty ${dbSapphire}
#echo "Key = ${dbSapphire} ; Value = " ${prop_value}
sapphire=${prop_value}
getProperty ${dbUdr}
#echo "Key = ${dbUdr} ; Value = " ${prop_value}
udr=${prop_value}
getProperty ${dbCsetllogs}
#echo "Key = ${dbCsetllogs} ; Value = " ${prop_value}
csetllogs=${prop_value}
echo "Enter database user id: "
echo ${username}
echo "Enter database user's password: "
echo ${password}
echo "Enter database host name or IP address: "
echo ${host}
echo "Enter database schema name for MS CORE (ex. ms): "
echo ${ms}
echo "Enter database schema name for Sapphire (ex. sapphire): "
echo ${sapphire}
echo "Enter database schema name for UDR (ex. udr): "
echo ${udr}
echo "Enter database schema name for CS etl log (ex. ms_etl_logs): "
echo ${csetllogs}
# Define variables
hashsep="#################################################################"
linesep="-----------------------------------------------------------------"
mysqlcmde="mysql -u${username} -p${password} -h${host}"
# Test connection to the database
echo $linesep
echo "$(date): Testing connection to the database.."
testconnection=$(mysql -u${username} -p${password} -s -N <<QUERY_INPUT
    SELECT 1 FROM dual;
QUERY_INPUT
)
if [ $testconnection=1]; then
	echo "Connection to database succeeded."
else
	echo "Connection to database failed. Check the user's credentials.."
	exit 1
fi
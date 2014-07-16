#!/bin/sh
#
# Copyright (C) 2012 Medsight Solutions Inc.
# author	: Sharad Prakash
# user updated	:
# date updated	: 23-Aug-2012
# Description	: This script creates the MS and Sapphire Schema
#####################################################################
. /script/project.properties
echo "####################################################################"
echo "Welcome to the Medsight Solutions Sapphire Schema Creation Program"
echo "Copyright (c) 2012 Medsight solutions inc."
echo "Curent time: $(date)" 
echo "####################################################################"
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
mysqlcmde="mysql -u$dbUser -p$dbPassword -h$dbHost -e "
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
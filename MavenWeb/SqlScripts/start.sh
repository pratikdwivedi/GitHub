#!/bin/sh
#
# Copyright (C) 2012 Medsight Solutions Inc.
# author	: Pratik Dwivedi
# user updated	:
# date updated	: 01-May-2014
# Description	: This script creates the MS and Sapphire Schema
#####################################################################


echo "####################################################################"
echo "Welcome to the Medsight Solutions Sapphire Schema Creation Program"
echo "Copyright (c) 2012 Medsight solutions inc."
echo "Curent time: $(date)" 
echo "####################################################################"
echo "Enter database user id: "
read dbUser
echo "Enter database user's password: "
stty -echo
read dbPassword
stty echo
echo "Enter database host name or IP address: "
read dbHost
echo "Enter database schema name for MS CORE (ex. ms): "
read dbtest

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

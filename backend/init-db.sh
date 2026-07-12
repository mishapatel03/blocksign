#!/bin/bash
set -e

# Function to create database if it doesn't exist
create_database() {
    local db_name=$1
    psql -v ON_ERROR_STOP=1 --username postgres --dbname postgres <<-EOSQL
        SELECT 'CREATE DATABASE $db_name' 
        WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = '$db_name')\gexec
EOSQL
}

# Create all required databases
create_database "blocksign_auth"
create_database "blocksign_docs"
create_database "blocksign_signing"
create_database "blocksign_blockchain"
create_database "blocksign_audit"

echo "Databases created successfully"

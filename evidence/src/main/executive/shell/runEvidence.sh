#!/bin/bash


dirpath="$(cd "$(dirname "$0")" && pwd)"

Evidence="$dirpath/evidence"

    	
DeployAddress=`${Evidence} "deploy" "user.jks" "123456" "123456" |grep "deploy factoryContract success"|awk -F " " '{print $5}'`
echo "deploy factoryContract success, address:${DeployAddress}"	
 

NewEvidenceAddress=`${Evidence} "new" "user.jks" "123456" "123456" ${DeployAddress} "67" "67"|grep "newEvidence success"|awk -F " " '{print $4}'`
echo "newEvidence success, newEvidenceAddress:${NewEvidenceAddress}"	

	
${Evidence} "send" "depositor.jks" "123456" "123456" ${NewEvidenceAddress}
${Evidence} "send" "arbitrator.jks" "123456" "123456" ${NewEvidenceAddress}

	
${Evidence} "get" "user.jks" "123456" "123456" ${NewEvidenceAddress}

	
${Evidence} "verify" "user.jks" "123456" "123456" ${NewEvidenceAddress}




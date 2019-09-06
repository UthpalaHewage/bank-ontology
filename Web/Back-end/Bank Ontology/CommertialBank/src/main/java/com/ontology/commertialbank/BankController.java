package com.ontology.commertialbank;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
	//get Customers Only By Bank Name
		@CrossOrigin
		@RequestMapping(method = RequestMethod.POST, value="/getbybank")
		public @ResponseBody String getByBankName(@RequestBody BankDetails bank) {
			
			String query="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
					"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
					"PREFIX bank:<http://localhost/ontologies/commercialbank.owl#>\r\n" + 
					"\r\n" + 
					"SELECT DISTINCT ?user ?firstName ?lastName\r\n" + 
					"WHERE {	\r\n" + 
					"  	bank:"+bank.bankName +" bank:consistsOf ?branch.\r\n" + 
					"  	?account bank:hasBranch ?branch.\r\n" + 
					" 	{ \r\n" + 
					"      {\r\n" + 
					"      	?user  bank:hasDepositeAccount ?account.\r\n" + 
					"      }\r\n" + 
					"    union\r\n" + 
					"      {\r\n" + 
					"      	?user  bank:hasLendingAccount ?account.\r\n" + 
					"      }\r\n" + 
					"  	}\r\n" + 
					" 	?user  bank:first_name ?firstName.\r\n" + 
					"  	?user  bank:last_name ?lastName.\r\n" + 
					"  	\r\n" + 
					"}";
//			System.out.println(bank.bankName);

			return OwlHelper.OwlToJson(query);		
		}
		
		//Get Customers By Bank Name and Branch Location
		@CrossOrigin
		@RequestMapping(method = RequestMethod.POST, value="/getbybankbranch")
		public @ResponseBody String getByBankAndBranch(@RequestBody BankDetails bank) {
			
			String query="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
					"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
					"PREFIX bank: <http://localhost/ontologies/commercialbank.owl#>\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"SELECT DISTINCT ?user ?firstName ?lastName\r\n" + 
					"WHERE {	\r\n" + 
					"  	bank:"+bank.bankName+" bank:consistsOf ?branch.\r\n" + 
					"  	?branch bank:branch_city"+"\""+bank.branchLocation+"\"." + 
					"  	?account bank:hasBranch ?branch.\r\n" + 
					" 	{ \r\n" + 
					"      {\r\n" + 
					"      	?user  bank:hasDepositeAccount ?account.\r\n" + 
					"      }\r\n" + 
					"    union\r\n" + 
					"      {\r\n" + 
					"      	?user  bank:hasLendingAccount ?account.\r\n" + 
					"      }\r\n" + 
					"  	}\r\n" + 
					" 	?user  bank:first_name ?firstName;\r\n" + 
					"		   bank:last_name ?lastName.\r\n" + 
					"  	\r\n" + 
					"}";
			return OwlHelper.OwlToJson(query);		
		}
		
		//Get Customers By Branch Location
			@CrossOrigin
			@RequestMapping(method = RequestMethod.POST, value="/getbybranch")
			public @ResponseBody String getByBranch(@RequestBody BankDetails bank) {
				
				String query="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
						"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
						"PREFIX bank: <http://localhost/ontologies/commercialbank.owl#>\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"SELECT DISTINCT ?user ?firstName ?lastName\r\n" + 
						"WHERE {	\r\n" + 
						"\r\n" + 
						"  	?branch bank:branch_city"+"\""+bank.branchLocation+"\"." + 
						"  ?account  bank:hasBranch ?branch.\r\n" + 
						"  \r\n" + 
						"  {\r\n" + 
						"    { ?account bank:lendingAccountFor ?user.}\r\n" + 
						"    union\r\n" + 
						"    { ?account bank:depositeAccountFor ?user.}\r\n" + 
						"  }\r\n" + 
						" \r\n" + 
						"\r\n" + 
						" \r\n" + 
						"  ?user bank:first_name ?firstName;\r\n" + 
						"         bank:last_name ?lastName\r\n" + 
						"  \r\n" + 
						"}";
				return OwlHelper.OwlToJson(query);		
			}
		
			//Get Customers By Account
			@CrossOrigin
			@RequestMapping(method = RequestMethod.POST, value="/getbyaccount")
			public @ResponseBody String getByAccount(@RequestBody BankDetails bank) {
				
				String query="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
						"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
						"PREFIX bank: <http://localhost/ontologies/commercialbank.owl#>\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"SELECT DISTINCT ?user ?firstName ?lastName\r\n" + 
						"WHERE {	\r\n" + 
						"  ?account rdf:type bank:"+bank.accountType+".\r\n" + 
						"  ?account  bank:hasBranch ?branch.\r\n" + 
						"  ?bankName bank:consistsOf ?branch.\r\n" + 
						"  {\r\n" + 
						"    { ?account bank:lendingAccountFor ?user.}\r\n" + 
						"    union\r\n" + 
						"    { ?account bank:depositeAccountFor ?user.}\r\n" + 
						"  }\r\n" + 
						" \r\n" + 
						"\r\n" + 
						" \r\n" + 
						"  ?user bank:first_name ?firstName;\r\n" + 
						"         bank:last_name ?lastName\r\n" + 
						"  \r\n" + 
						"}";
				return OwlHelper.OwlToJson(query);		
			}
			
			//Get Customers By Account and Branch
			@CrossOrigin
			@RequestMapping(method = RequestMethod.POST, value="/getbybranchaccount")
			public @ResponseBody String getByBranchAndAccount(@RequestBody BankDetails bank) {
				
				String query="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
						"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
						"PREFIX bank: <http://localhost/ontologies/commercialbank.owl#>\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"SELECT DISTINCT ?user ?firstName ?lastName\r\n" + 
						"WHERE {	\r\n" + 
						"  ?account rdf:type bank:"+bank.accountType+".\r\n" + 
						"  ?account  bank:hasBranch ?branch.\r\n" + 
						"  	?branch bank:branch_city"+"\""+bank.branchLocation+"\"." + 
						"  {\r\n" + 
						"    { ?account bank:lendingAccountFor ?user.}\r\n" + 
						"    union\r\n" + 
						"    { ?account bank:depositeAccountFor ?user.}\r\n" + 
						"  }\r\n" + 
						" \r\n" + 
						"\r\n" + 
						" \r\n" + 
						"  ?user bank:first_name ?firstName;\r\n" + 
						"         bank:last_name ?lastName\r\n" + 
						"  \r\n" + 
						"}";
				return OwlHelper.OwlToJson(query);		
			}
			
			
			//Get Customers By Account and Bank
			@CrossOrigin
			@RequestMapping(method = RequestMethod.POST, value="/getbybankaccount")
			public @ResponseBody String getByBankAndAccount(@RequestBody BankDetails bank) {
				
				String query="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
						"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
						"PREFIX bank: <http://localhost/ontologies/commercialbank.owl#>\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"SELECT DISTINCT ?user ?firstName ?lastName\r\n" + 
						"WHERE {	\r\n" + 
						"  ?account rdf:type bank:"+bank.accountType+".\r\n" + 
						"  ?account  bank:hasBranch ?branch.\r\n" + 
						"  bank:"+bank.bankName+" bank:consistsOf ?branch.\r\n" + 
						"  {\r\n" + 
						"    { ?account bank:lendingAccountFor ?user.}\r\n" + 
						"    union\r\n" + 
						"    { ?account bank:depositeAccountFor ?user.}\r\n" + 
						"  }\r\n" + 
						" \r\n" + 
						"\r\n" + 
						" \r\n" + 
						"  ?user bank:first_name ?firstName;\r\n" + 
						"         bank:last_name ?lastName\r\n" + 
						"  \r\n" + 
						"}";
				return OwlHelper.OwlToJson(query);		
			}
			
			//Get Customers By Account and Branch and Bank
			@CrossOrigin
			@RequestMapping(method = RequestMethod.POST, value="/getbybankbranchaccount")
			public @ResponseBody String getByBankAndBranchAndAccount(@RequestBody BankDetails bank) {
				
				String query="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
						"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
						"PREFIX bank: <http://localhost/ontologies/commercialbank.owl#>\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"SELECT DISTINCT ?user ?firstName ?lastName\r\n" + 
						"WHERE {	\r\n" + 
						"  ?account rdf:type bank:"+bank.accountType+".\r\n" + 
						"  ?account  bank:hasBranch ?branch.\r\n" + 
						"  bank:"+bank.bankName+" bank:consistsOf ?branch.\r\n" + 
						"  	?branch bank:branch_city"+"\""+bank.branchLocation+"\"." + 
						"  {\r\n" + 
						"    { ?account bank:lendingAccountFor ?user.}\r\n" + 
						"    union\r\n" + 
						"    { ?account bank:depositeAccountFor ?user.}\r\n" + 
						"  }\r\n" + 
						" \r\n" + 
						"\r\n" + 
						" \r\n" + 
						"  ?user bank:first_name ?firstName;\r\n" + 
						"         bank:last_name ?lastName\r\n" + 
						"  \r\n" + 
						"}";
				return OwlHelper.OwlToJson(query);		
			}
			
			
		//Get Customers By Account and Branch and Bank
		@CrossOrigin
		@RequestMapping(method = RequestMethod.GET, value="/getByCustomerId/{id}")
		public @ResponseBody String getByCustomerId(@PathVariable String id) {
			
			String query="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
					"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
					"PREFIX bank: <http://localhost/ontologies/commercialbank.owl#>\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"SELECT ?firstName ?lastName ?bankName ?branchCity ?accountName  ?accountNo ?accountBalance \r\n" + 
					"WHERE {	\r\n" + 
					"\r\n" + 
					"  ?customer bank:id_number"+"\""+id+"\".\r\n" + 
					"  ?customer bank:first_name ?firstName;\r\n" + 
					"            bank:last_name ?lastName\r\n" + 
					"  {\r\n" + 
					"    { ?account bank:lendingAccountFor ?customer.}\r\n" + 
					"    union\r\n" + 
					"    { ?account bank:depositeAccountFor ?customer.}\r\n" + 
					"  }\r\n" + 
					"  \r\n" + 
					"  ?account bank:account_no ?accountNo;\r\n" + 
					"  		   bank:account_balance ?accountBalance;\r\n" + 
					"      	   bank:account_name ?accountName;\r\n" + 
					"           bank:hasBranch    ?branchName.\r\n" + 
					"  ?branchName bank:branch_city ?branchCity.\r\n" + 
					"  ?bank   bank:consistsOf ?branchName;\r\n" + 
					"  		  bank:bank_name  ?bankName.\r\n" + 
					"  \r\n" + 
					"}";
			return OwlHelper.OwlToJson(query);	
//			System.out.println(id);
//			return id;
		}
}

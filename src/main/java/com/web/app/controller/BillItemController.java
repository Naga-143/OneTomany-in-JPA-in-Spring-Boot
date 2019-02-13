package com.web.app.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.web.app.model.BillDetail;
import com.web.app.model.BillItem;
import com.web.app.repository.BillDetailRepository;

@RestController
public class BillItemController {

	@Autowired
	private BillDetailRepository billdetailrepository;
	

	
	@RequestMapping(value = "/addBill", method= RequestMethod.POST)
	public ResponseEntity<?> AddBill(@RequestBody Map<String, Object> billDetails){	
		
		
		Map<String, Object> bdFromReq = (java.util.Map<String, Object>) billDetails.get("billDetail");
		
		BillDetail billDetailObj = new BillDetail();
		billDetailObj.setBillName((String) bdFromReq.get("billName"));
		billDetailObj.setBillDate(new Date ((String) bdFromReq.get("billDate")));
		billDetailObj.setBillTotal(Float.parseFloat((String) bdFromReq.get("billTotal")));
				
		Set<BillItem> billItemSet = new HashSet<BillItem>();
		
		for(Map<String, Object> billItems : (List<Map<String, Object>>) billDetails.get("billItems")) {
			BillItem  billItem = new BillItem();
			billItem.setBillItem((String) billItems.get("billItem"));
			billItem.setBillItemQty(Integer.parseInt((String) billItems.get("billItemQty")));
			billItem.setBillItemRate(Float.parseFloat((String)billItems.get("billItemRate")));
			billItem.setBillDetail(billDetailObj);
			billItemSet.add(billItem);		
		}       
        
		billDetailObj.setBillItems(billItemSet);		
		billdetailrepository.save(billDetailObj);	
		
        return new ResponseEntity<>(billDetailObj, HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "editBill/{id}", method= RequestMethod.GET)
	public Map<String, Object> EditBill(@PathVariable long id){	
		
			BillDetail billDetail = billdetailrepository.findOne(id);	
			
            Set<BillItem> billItems = billDetail.getBillItems(); 
            
            Map<String, Object> maps = new HashMap<>();
			maps.put("billDetail", billDetail);			
			maps.put("billItems", billItems);            
            
            return maps;
	}
	
	

	@RequestMapping(value = "updateBill", method= RequestMethod.PUT)
	public ResponseEntity<?> updateBill(@RequestBody Map<String, Object> billDetails){	
		
		Map<String, Object> bdFromReq = (java.util.Map<String, Object>) billDetails.get("billDetail");
		
		
		BillDetail billDetailObj = new BillDetail();
		billDetailObj.setBillId(Long.parseLong((String) bdFromReq.get("billId")) );
		billDetailObj.setBillName((String) bdFromReq.get("billName"));
		billDetailObj.setBillDate(new Date ((String) bdFromReq.get("billDate")));
		billDetailObj.setBillTotal(Float.parseFloat((String) bdFromReq.get("billTotal")));
				
		Set<BillItem> billItemSet = new HashSet<BillItem>();
		
		for(Map<String, Object> billItems : (List<Map<String, Object>>) billDetails.get("billItems")) {
			BillItem  billItem = new BillItem();
			billItem.setBillItem((String) billItems.get("billItem"));
			billItem.setBillItemQty(Integer.parseInt((String) billItems.get("billItemQty")));
			billItem.setBillItemRate(Float.parseFloat((String)billItems.get("billItemRate")));
			billItem.setBillDetail(billDetailObj);
			billItemSet.add(billItem);		
		}       
        
		billDetailObj.setBillItems(billItemSet);		
		billdetailrepository.save(billDetailObj);	
		
		
        return new ResponseEntity<>(billDetailObj, HttpStatus.CREATED);
    }
	
	
	@RequestMapping(value = "deleteBill/{id}", method= RequestMethod.DELETE)
	public String DeleteBill(@PathVariable long id){		
			billdetailrepository.delete(id);	
            return "Deleted Successfully";
	}
	
	@RequestMapping(value = "allBills", method= RequestMethod.GET)
	public ResponseEntity<?> GetBills(){
			return new ResponseEntity<>(billdetailrepository.findAll(), HttpStatus.CREATED);
	}
	
	
	// Hard Code To add bill

	@RequestMapping(value = "/addBillTest", method= RequestMethod.POST)
	public void addBills() {
		
		BillDetail billDetail = new BillDetail();
		billDetail.setBillName("Naga");
		billDetail.setBillTotal((float) 10.50);
		
		BillItem billItem = new BillItem();
		billItem.setBillItem("Parota");
		billItem.setBillItemQty(10);
		billItem.setBillItemRate(1000);
		billItem.setBillDetail(billDetail);
		
		BillItem billItem2 = new BillItem();
		billItem2.setBillItem("Panana");
		billItem2.setBillItemQty(50);
		billItem2.setBillItemRate(5000);
		billItem2.setBillDetail(billDetail);
		billItem2.setBillDetail(billDetail);
		
		Set<BillItem> billItemsSet = new HashSet<>();
		billItemsSet.add(billItem);
		billItemsSet.add(billItem2);
		
		billDetail.setBillItems(billItemsSet);
		billdetailrepository.save(billDetail);
		
	}














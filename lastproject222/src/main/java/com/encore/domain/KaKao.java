package com.encore.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class KaKao {
	  //response
    private String tid, next_redirect_pc_url;
    private Date created_at;
}

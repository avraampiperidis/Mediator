/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.mediator;

/**
 *
 * @author piper
 */
public class Mock {
    
    public static final String JSON_OB = "{\n" +
"    \"name\": \"model\",\n" +
"    \"num\": 2017,\n" +
"    \"subModel\": {\n" +
"        \"subName\": \"subModel1\",\n" +
"        \"status\": false\n" +
"    },\n" +
"    \"subModels\": [\n" +
"        {\n" +
"            \"subName\": \"subModel2\",\n" +
"            \"status\": false\n" +
"        },\n" +
"        {\n" +
"            \"subName\": \"subModel3\",\n" +
"            \"status\": false\n" +
"        },\n" +
"        {\n" +
"            \"subName\": \"subModel1\",\n" +
"            \"subNumber\": 1234,\n" +
"            \"status\": false,\n" +
"            \"values\": [\n" +
"                \"one\",\n" +
"                \"two\"\n" +
"            ],\n" +
"            \"subSubmodel\": {\n" +
"                \"subNumber\": 12,\n" +
"                \"status\": true\n" +
"            }\n" +
"        }\n" +
"    ],\n" +
"    \"values\": [\n" +
"        \"val1\",\n" +
"        \"val2\",\n" +
"        \"val3\",\n" +
"        \"val4\"\n" +
"    ]\n" +
"}";
    
    
    public static final String JSON_ARR = "[\n" +
"    {\n" +
"        \"name\": \"model\",\n" +
"        \"num\": 2018,\n" +
"        \"subModel\": {\n" +
"            \"subName\": \"subModel1\",\n" +
"            \"status\": false\n" +
"        },\n" +
"        \"subModels\": [\n" +
"            {\n" +
"                \"subName\": \"subModel3\",\n" +
"                \"status\": false\n" +
"            },\n" +
"            {\n" +
"                \"subName\": \"subModel4\",\n" +
"                \"status\": false\n" +
"            },\n" +
"            {\n" +
"                \"subName\": \"subModel2\",\n" +
"                \"subNumber\": 1234,\n" +
"                \"status\": false,\n" +
"                \"values\": [\n" +
"                    \"one\",\n" +
"                    \"two\"\n" +
"                ],\n" +
"                \"subSubmodel\": {\n" +
"                    \"subNumber\": 12,\n" +
"                    \"status\": true\n" +
"                }\n" +
"            }\n" +
"        ],\n" +
"        \"values\": [\n" +
"            \"val1\",\n" +
"            \"val2\",\n" +
"            \"val3\",\n" +
"            \"val4\"\n" +
"        ]\n" +
"    },\n" +
"    {\n" +
"        \"name\": \"model\",\n" +
"        \"num\": 2020,\n" +
"        \"subModel\": {\n" +
"            \"subName\": \"subModel1\",\n" +
"            \"status\": false\n" +
"        },\n" +
"        \"subModels\": [\n" +
"            {\n" +
"                \"subName\": \"subModel5\",\n" +
"                \"status\": false\n" +
"            },\n" +
"            {\n" +
"                \"subName\": \"subModel6\",\n" +
"                \"status\": false\n" +
"            },\n" +
"            {\n" +
"                \"subName\": \"subModel4\",\n" +
"                \"subNumber\": 1234,\n" +
"                \"status\": false,\n" +
"                \"values\": [\n" +
"                    \"one\",\n" +
"                    \"two\"\n" +
"                ],\n" +
"                \"subSubmodel\": {\n" +
"                    \"subNumber\": 12,\n" +
"                    \"status\": true\n" +
"                }\n" +
"            }\n" +
"        ],\n" +
"        \"values\": [\n" +
"            \"val1\",\n" +
"            \"val2\",\n" +
"            \"val3\",\n" +
"            \"val4\"\n" +
"        ]\n" +
"    },\n" +
"    {}\n" +
"]";
    
    
    
    public static final String SKELETON_ALL = "{\n" +
"  \"type1\": \"string\",\n" +
"  \"type2\": \"number\",\n" +
"  \"type3\": {\n" +
"    \"type4\": \"string\",\n" +
"    \"type5\": \"boolean\"\n" +
"  },\n" +
"  \"type17\": [\n" +
"    {\n" +
"      \"type6\": \"string\",\n" +
"      \"type7\": \"boolean\"\n" +
"    },\n" +
"    {\n" +
"      \"type8\": \"string\",\n" +
"      \"type9\": \"boolean\"\n" +
"    },\n" +
"    {\n" +
"      \"type10\": \"string\",\n" +
"      \"type11\": \"number\",\n" +
"      \"type12\": \"boolean\",\n" +
"      \"type13\": [\n" +
"        \"string\",\n" +
"        \"string\"\n" +
"      ],\n" +
"      \"type14\": {\n" +
"        \"type15\": \"number\",\n" +
"        \"type16\": \"boolean\"\n" +
"      }\n" +
"    }\n" +
"  ],\n" +
"  \"type18\": [\n" +
"    \"string\",\n" +
"    \"string\",\n" +
"    \"string\",\n" +
"    \"string\"\n" +
"  ]\n" +
"}";
    
    
    
    public static final String JSON_3 = "{\n" +
"    \"status\": {},\n" +
"    \"id\": 3\n" +
"}";
    
    
    
}

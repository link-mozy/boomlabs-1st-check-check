var express = require('express');
const { resume } = require('../database/connect/maria');
var router = express.Router();

// require maria.js
const maria = require('../database/connect/maria');

// smart contract send
const sc = require('../txApp');

/* POST issue-certificate page. */
router.post('/issue-certificate', async function(req, res) {
    const {discord_channel_id, condition_count, token_uri} = req.body;
    let query = `
    SELECT 
    *
    FROM 
    (
        SELECT 
            u.discord_id AS discord_id,
            u.user_address  AS user_address,
            COUNT(ci.user_id) AS check_in_count
        FROM 
            check_in ci , users u
        WHERE ci.user_id = u.user_id 
        AND ci.discord_channel_id = '${discord_channel_id}'
        GROUP BY ci.user_id 
    ) AS check_in_list
    WHERE check_in_count >= ${condition_count}
    `; // discord_id, user_address, check_in_count
    
    // 조건에 맞는 사람의 지갑 주소 가져오기
    maria.query(query, async (err, rows, fields) => {
        const _user_address_list = []; // 조건에 맞는 사람의 지갑 주소를 갖고있는 리스트
        if(!err) {
            resutl = await rows;
            if(rows != undefined && rows != null) {
                await rows.map(async (row, idx) => {
                    let _user_address = await row.user_address;
                    _user_address_list.push(_user_address);
                })
            }
        } else {
            console.log("issue-certificate query error : ", err);
        }

        // 컨트랙트 통신 부르기
        await sc.safeMint(_user_address_list, token_uri);
        console.log(_user_address_list);
    })

    // let body = {a: 1, b: 2};
    res.send("contract 요청");
});

module.exports = router;
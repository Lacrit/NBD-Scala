printjson(db.people.aggregate([
    { $unwind: "$credit"},
    { $group: {_id:"$credit.currency", balance_left: {$sum: {$toDecimal: "$credit.balance"}}}}
]).toArray());

printjson(db.people.aggregate([
    { $match: {nationality : "Poland", sex: "Female"}},
    { $unwind: "$credit"},
    { $group: {_id:"$credit.currency",
            balance_left: {$sum: {$toDecimal: "$credit.balance"}},
            average_left: {$avg: {$toDecimal: "$credit.balance"}}
    }}
]).toArray());

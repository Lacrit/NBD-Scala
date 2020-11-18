printjson(db.people.aggregate(
    [
        {
            $group:{ _id:"$sex", height_average: { $avg: {$toDecimal: "$height"}}, weight_average: { $avg: {$toDecimal: "$weight"}}}
        }
    ]
).toArray());

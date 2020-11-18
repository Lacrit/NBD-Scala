printjson(db.people.aggregate([
    { $project: {
        nationality: "$nationality",
        bmi: { $divide: [
                {$toDecimal: "$weight"},
                {$pow: [{$divide: [{$toDecimal: "$height"}, 100]}, 2]}
            ]}
    }},
    { $group : {_id: "$nationality",
            average: { $avg: "$bmi"},
            minimal: { $min: "$bmi"},
            maximal: { $max: "$bmi"}
}}]).toArray());

maps = function() {
    emit(this.job, 1)
}

db.people.mapReduce(
    maps,
    function(){},
    {
        out: "task3",
    }
    );

printjson(db.task3.find().toArray());

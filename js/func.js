function changeColor(obj)
{
    if (obj.style.color == "black")
    {
        obj.style.color = "red";
    }
    else
    {
        obj.style.color = "black";
    }
}

function showName()
{
    var user = document.getElementById("name").value;
    alert("用户名是："+user);
}
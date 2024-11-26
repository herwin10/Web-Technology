<?php
$menuXML = simplexml_load_file('menu.xml') or die("Error: Cannot load menu.xml");
session_start();
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Foodie Spot - Menu</title>
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            margin: 0;
            padding: 0;
            background: #f8f9fa;
            color: #333;
        }

        h1 {
            text-align: center;
            margin: 20px 0;
            color: #ff6bcb;
        }

        .container {
            width: 90%;
            max-width: 800px;
            margin: 0 auto;
            background: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 8px 15px rgba(0, 0, 0, 0.1);
        }

        .menu-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 0;
            border-bottom: 1px solid #ddd;
        }

        .menu-item:last-child {
            border-bottom: none;
        }

        .menu-item h3 {
            margin: 0;
            font-size: 1.2em;
            color: #555;
        }

        .menu-item p {
            margin: 5px 0;
            font-size: 0.9em;
            color: #777;
        }

        button {
            background: #ff6bcb;
            color: #fff;
            border: none;
            padding: 8px 15px;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s ease;
        }

        button:hover {
            background: #ff4081;
        }
    </style>
</head>
<body>
    <h1>Foodie Spot - Explore Our Menu</h1>
    <div class="container">
        <form method="post" action="order1.php">
            <?php foreach ($menuXML->item as $item): ?>
                <div class="menu-item">
                    <div>
                        <h3><?php echo $item->name; ?> - ₹<?php echo $item->price; ?></h3>
                        <p><?php echo $item->description; ?></p>
                    </div>
                    <div>
                        <label>
                            <input type="checkbox" name="selected_items[]" value="<?php echo $item->name; ?>"> Select
                        </label>
                        <input type="number" name="quantity[<?php echo $item->name; ?>]" min="0" value="1">
                    </div>
                </div>
            <?php endforeach; ?>
            <button type="submit">Place Order</button>
        </form>
    </div>
</body>
</html>

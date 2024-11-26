<?php
session_start();
$orderDetails = $_SESSION['order_details'] ?? [];
$total = $_SESSION['total'] ?? 0;
session_unset();
session_destroy();
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Foodie Spot - Order Confirmation</title>
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            margin: 0;
            padding: 0;
            background: linear-gradient(135deg, #ff9a76, #ff6bcb);
            color: #fff;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            width: 90%;
            max-width: 600px;
            background: #fff;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 8px 15px rgba(0, 0, 0, 0.2);
            text-align: center;
        }

        h1 {
            color: #ff6bcb;
        }

        h3 {
            color: #333;
            margin-top: 20px;
        }

        ul {
            list-style: none;
            padding: 0;
            color: #555;
        }

        ul li {
            margin-bottom: 10px;
        }

        .total {
            font-size: 1.5em;
            font-weight: bold;
            color: #2a9d8f;
            margin-top: 20px;
        }

        a {
            display: inline-block;
            text-decoration: none;
            background: #ff6bcb;
            color: #fff;
            padding: 10px 20px;
            border-radius: 5px;
            margin-top: 20px;
            transition: background 0.3s ease;
        }

        a:hover {
            background: #ff4081;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Order Confirmation</h1>
        <?php if (!empty($orderDetails)): ?>
            <h2>Your Order:</h2>
            <ul>
                <?php foreach ($orderDetails as $detail): ?>
                    <li><?php echo $detail; ?></li>
                <?php endforeach; ?>
            </ul>
            <div class="total">Total: ₹<?php echo $total; ?></div>
            <p>Thank you for ordering with Foodie Spot! 😊</p>
        <?php else: ?>
            <p>No order details found. Please try again.</p>
        <?php endif; ?>
        <a href="menu1.php">Back to Menu</a>
    </div>
</body>
</html>

INSERT INTO
    categories (name)
VALUES ('MFD Panels'),
    ('Button Boxes'),
    ('HOTAS'),
    ('Rudder Pedals'),
    ('Displays'),
    ('Cockpit Controls');

INSERT INTO
    products (
        name,
        price,
        description,
        category_id
    )
VALUES (
        'Thrustmaster MFD Cougar Pack',
        89.99,
        'Pair of programmable MFD frames inspired by modern fighter aircraft. Compatible with DCS, MSFS, and X-Plane.',
        1
    ),
    (
        'Winwing MIP UFC Panel',
        399.99,
        'Replica Up Front Controller panel for the F/A-18C with backlit buttons and USB connectivity.',
        1
    ),
    (
        'Virpil Control Panel #2',
        169.95,
        'Programmable button box featuring guarded switches, rotary encoders, and toggle switches.',
        2
    ),
    (
        'Black Hog B-Explorer Button Box',
        249.99,
        'Professional-grade button box with aircraft-style switches designed for combat flight simulators.',
        2
    ),
    (
        'Thrustmaster HOTAS Warthog',
        549.99,
        'Replica A-10C HOTAS system featuring metal construction and Hall effect sensors.',
        3
    ),
    (
        'Winwing Orion 2 HOTAS Max',
        649.99,
        'High-end HOTAS setup with customizable grips and precision sensors for DCS pilots.',
        3
    ),
    (
        'MFG Crosswind V3 Rudder Pedals',
        329.00,
        'Adjustable rudder pedals with contactless sensors and smooth aircraft-grade mechanics.',
        4
    ),
    (
        'Thrustmaster TPR Pendular Rudder',
        549.99,
        'Premium pendular rudder system designed for maximum realism and precision.',
        4
    ),
    (
        'Lilliput A8 8-inch HDMI Display',
        199.99,
        'Compact touchscreen display commonly used for exporting DCS MFDs and cockpit instruments.',
        5
    ),
    (
        'Winwing PTO 2 Takeoff Panel',
        219.99,
        'Aircraft-inspired takeoff and landing control panel with authentic switch layouts.',
        6
    );
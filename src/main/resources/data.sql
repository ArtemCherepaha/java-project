INSERT INTO `manufacturer` (`id`, `contact_number`, `country`, `name`) VALUES
(1, 'Contact number 1', 'Ukraine', 'Manufacturer 1'),
(2, 'Contact number 2', 'USA', 'Manufacturer 2'),
(3, 'Contact number 3', 'Germany', 'Manufacturer 3');

INSERT INTO `equipment_details` (`id`, `current_power`, `has_error`, `last_contact_date`) VALUES
(4, 100, b'1', DATE_SUB(NOW(), INTERVAL 2 HOUR)),
(5, 11, b'0', DATE_SUB(NOW(), INTERVAL 1 HOUR)),
(6, 70, b'0', DATE_SUB(NOW(), INTERVAL 1 MINUTE)),
(7, 28, b'0', DATE_SUB(NOW(), INTERVAL 30 MINUTE)),
(8, 51, b'1', DATE_SUB(NOW(), INTERVAL 12 HOUR)),
(9, 78, b'1', DATE_SUB(NOW(), INTERVAL 7 HOUR)),
(10, 48, b'0', DATE_SUB(NOW(), INTERVAL 25 MINUTE)),
(11, 15, b'1', DATE_SUB(NOW(), INTERVAL 8 HOUR));

INSERT INTO `equipment` (`id`, `equipment_type`, `serial_number`, `details_id`, `manufacturer_id`) VALUES
(12, 'Type 1', 'b9a53a48-b89b-11eb-8529-0242ac130003', 4, 2),
(13, 'Type 2', 'b9a53cdc-b89b-11eb-8529-0242ac130003', 5, 1),
(14, 'Type 1', 'b9a53dd6-b89b-11eb-8529-0242ac130003', 6, 3),
(15, 'Type 3', 'b9a5438a-b89b-11eb-8529-0242ac130003', 7, 3),
(16, 'Type 2', 'b9a54484-b89b-11eb-8529-0242ac130003', 8, 1),
(17, 'Type 3', 'b9a5475e-b89b-11eb-8529-0242ac130003', 9, 2),
(18, 'Type 3', 'b9a54826-b89b-11eb-8529-0242ac130003', 10, 2),
(19, 'Type 1', 'b9a548e4-b89b-11eb-8529-0242ac130003', 11, 1);

UPDATE hibernate_sequence
SET next_val = 20
WHERE next_val = 1;

DELIMITER $$

CREATE
    EVENT `update_equipment_status`
    ON SCHEDULE EVERY 1 HOUR STARTS NOW()
    DO BEGIN

        UPDATE equipment_details det
        SET det.has_error = 1
        WHERE TIMESTAMPDIFF(HOUR, det.last_contact_date, NOW()) >= 1;

        UPDATE equipment_details det
        SET det.has_error = 0
        WHERE TIMESTAMPDIFF(HOUR, det.last_contact_date, NOW()) < 1;

    END $$

DELIMITER ;
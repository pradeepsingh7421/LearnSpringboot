package com.learn.springboot.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.learn.springboot.Entity.Parent;
import com.learn.springboot.common.utils.Utils;
import com.learn.springboot.model.ParentRequest;
import com.learn.springboot.service.ParentService;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.imageio.ImageIO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/parent")
public class ParentController {

    private ParentService mParentService;

    public ParentController(ParentService pParentService) {
        this.mParentService = pParentService;
    }

    @PostMapping("/create")
    public Parent createParent(@RequestBody ParentRequest pParent) {
        System.out.println(pParent.getFirstName() + "..." + pParent.getLastName());
        return mParentService.createParent(pParent);
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getParent() {
        Parent lParent = mParentService.getParent();
        System.out.println(lParent);
        return ResponseEntity.status(HttpStatus.OK).body(lParent);
    }

    @PostMapping("/qrCode")
    public String generateQrCode() throws WriterException, IOException {
        String data = Utils.readFile("customerPayload.json");

        String path = "C:\\Users\\prade\\OneDrive\\Desktop\\New folder\\qrcode.jpg";
        BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500);
        MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
        String str = "Qr code generated successfully";
        return str;
    }

    @GetMapping("/qrCode")
    public String readQrCode() throws WriterException, IOException, NotFoundException {
        String path = "C:\\Users\\prade\\OneDrive\\Desktop\\New folder\\qrcode.jpg";

        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(path)))));
        Result result = new MultiFormatReader().decode(binaryBitmap);

        String str = "Data stored in qr code is-" + result.getText();
        return str;
    }

    @PostMapping("/qrCodePass")
    public String generateQrCodeWithPassword() throws Exception {
        String data = Utils.readFile("customerPayload.json");

        String password = "1111"; // Change this to your desired password

        // Generate a random secret key based on the password
        SecretKey secretKey = generateSecretKey(password);

        // Encrypt the data
        String encryptedData = encrypt(data, secretKey);

        // Path to save the QR code image
        String path = "C:\\Users\\prade\\OneDrive\\Desktop\\New folder\\qrcode2.jpg";

        // Combine encrypted data and key into a single string
        String encryptedString = encryptedData + "|" + Base64.getEncoder().encodeToString(secretKey.getEncoded());

        BitMatrix matrix = new MultiFormatWriter().encode(encryptedString, BarcodeFormat.QR_CODE, 500, 500);
        MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
        String str = "Qr code generated successfully";
        return str;
    }

    private static SecretKey generateSecretKey(String password) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        SecureRandom secureRandom = new SecureRandom(password.getBytes(StandardCharsets.UTF_8));

        keyGenerator.init(256); // You can change the key size as needed (e.g., 128, 256)
        return keyGenerator.generateKey();
    }

    private static String encrypt(String data, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
}

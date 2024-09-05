import Foundation
import Security

@objc public class KeyChainHelper: NSObject {

    @objc public static func save(key: String, value: String) -> Bool {
        let data = value.data(using: .utf8)!
        let query = [
            kSecClass: kSecClassGenericPassword,
            kSecAttrAccount: key,
            kSecValueData: data
        ] as [CFString : Any]

        SecItemDelete(query as CFDictionary)
        let status = SecItemAdd(query as CFDictionary, nil)
        return status == errSecSuccess
    }

    @objc public static func get(key: String) -> String? {
        let query = [
            kSecClass: kSecClassGenericPassword,
            kSecAttrAccount: key,
            kSecReturnData: kCFBooleanTrue!,
            kSecMatchLimit: kSecMatchLimitOne
        ] as [CFString : Any]

        var result: AnyObject?
        let status = SecItemCopyMatching(query as CFDictionary, &result)

        if status == errSecSuccess, let data = result as? Data {
            return String(data: data, encoding: .utf8)
        }
        return nil
    }

    @objc public static func remove(key: String) {
        let query = [
            kSecClass: kSecClassGenericPassword,
            kSecAttrAccount: key
        ] as [CFString : Any]

        SecItemDelete(query as CFDictionary)
    }

    @objc public static func clear() {
        let query = [
            kSecClass: kSecClassGenericPassword
        ] as [CFString : Any]

        SecItemDelete(query as CFDictionary)
    }
}

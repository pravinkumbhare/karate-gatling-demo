function fn() {
//  var config = {
//    // Base URL for all API calls
//    baseUrl: 'https://reqres.in/api',
//
//    // Global headers
//    headers: {
//      'Content-Type': 'application/json',
//      'x-api-key': 'reqres-free-v1'
//      // 'Authorization': 'Bearer YOUR_API_KEY'
//    },
//
//    // Example environment variables
//    userId: 2,
//    page: 2
//  };

var config = {
    // Base URL for all API calls
    baseUrl: karate.properties['baseUrl'] || 'http://localhost:8081/',

    // Global headers
    headers: {
      'Content-Type': 'application/json',
      'x-api-key': 'reqres-free-v1'
      // 'Authorization': 'Bearer YOUR_API_KEY'
    }

  };

// Configure SSL (outside of config object)
  karate.configure('ssl', true);

  // Retry strategy (optional, applies globally)
  karate.configure('retry', { count: 5, interval: 1000 });

  return config;
}
